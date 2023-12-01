package school.sptech.controller;

import org.json.JSONObject;
import school.sptech.banco.dao.ChamadoDao;
import school.sptech.integracoes.jira.JiraIntegracao;
import school.sptech.integracoes.slack.SlackIntegracao;
import school.sptech.model.Chamado;
import school.sptech.model.DadosServidor;
import school.sptech.model.Servidor;
import school.sptech.model.componentes.Componente;
import school.sptech.model.componentes.Cpu;
import school.sptech.model.componentes.Disco;
import school.sptech.model.componentes.Memoria;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ChamadosController {

    public static void compararServidorBanco(Servidor servidor) throws IOException, InterruptedException {
        List<DadosServidor> ultimosDados = servidor.getDadosServidorParaAberturaDeChamados();

        ContadorChamados contador = new ContadorChamados();
        contador.popularDados(ultimosDados);

        if (contador.alertasCpu > 1) {
            Chamado chamado = ChamadoDao.buscarChamadoAbertoPorServidorEComponente(servidor, 1);
            if (chamado == null) {
                JSONObject response = JiraIntegracao
                        .postRecurso("issue", "CPU", contador.maxCpu, servidor.getHostname());
                chamado = new Chamado(servidor.getIdServidor(), "CPU", response.getString("key"),
                        "Aberto", contador.maxCpu > 90);
                ChamadoDao.inserirChamado(chamado);
                SlackIntegracao.enviarMensagem("""
                        Foi aberto um novo chamado - Uso de %s está em nível %s
                        <%s|%s>   
                        """.formatted(chamado.getComponente(), chamado.getCritico() ? "Crítico" : "Alerta",
                            JiraIntegracao.BASE_URL_JIRA + "browse/" + chamado.getChaveJira(),
                            chamado.getChaveJira()));
            } else {
                if (!chamado.getCritico() && contador.maxCpu > 90) {
                    chamado.setCritico(true);
                    chamado.setUltimaMensagemSlack(LocalDateTime.now());

                    SlackIntegracao.enviarMensagem("""
                            Uso de CPU atingiu nível crítico:
                            Chamado: <%s|%s>
                            Estado: %s
                            """.formatted(JiraIntegracao.BASE_URL_JIRA + "browse/" + chamado.getChaveJira(),
                                chamado.getChaveJira(), chamado.getStatus()));
                    ChamadoDao.atualizarChamado(chamado);
                }
            }
        }

        if (contador.alertasRam > 1) {
            Chamado chamado = ChamadoDao.buscarChamadoAbertoPorServidorEComponente(servidor, 2);
            if (chamado == null) {
                JSONObject response = JiraIntegracao
                        .postRecurso("issue", "RAM", contador.maxRam, servidor.getHostname());
                chamado = new Chamado(servidor.getIdServidor(), "RAM", response.getString("key"),
                        "Aberto", contador.maxCpu > 90);
                ChamadoDao.inserirChamado(chamado);
                SlackIntegracao.enviarMensagem("""
                        Foi aberto um novo chamado - Uso de %s está em nível %s
                        <%s|%s>   
                        """.formatted(chamado.getComponente(), chamado.getCritico() ? "Crítico" : "Alerta",
                        JiraIntegracao.BASE_URL_JIRA + "browse/" + chamado.getChaveJira(),
                        chamado.getChaveJira()));
            } else {
                if (!chamado.getCritico() && contador.maxRam > 90) {
                    chamado.setCritico(true);
                    chamado.setUltimaMensagemSlack(LocalDateTime.now());

                    SlackIntegracao.enviarMensagem("""
                            Uso de RAM atingiu nível crítico:
                            Chamado: <%s|%s>
                            Estado: %s
                            """.formatted(JiraIntegracao.BASE_URL_JIRA + "browse/" + chamado.getChaveJira(),
                            chamado.getChaveJira(), chamado.getStatus()));
                    ChamadoDao.atualizarChamado(chamado);
                }
            }
        }

        if (contador.alertasDisco > 1) {
            Chamado chamado = ChamadoDao.buscarChamadoAbertoPorServidorEComponente(servidor, 3);
            if (chamado == null) {
                JSONObject response = JiraIntegracao
                        .postRecurso("issue", "Disco", contador.maxDisco, servidor.getHostname());
                chamado = new Chamado(servidor.getIdServidor(), "Disco", response.getString("key"),
                        "Aberto", contador.maxCpu > 90);
                ChamadoDao.inserirChamado(chamado);
                SlackIntegracao.enviarMensagem("""
                        Foi aberto um novo chamado - Uso de %s está em nível %s
                        <%s|%s>   
                        """.formatted(chamado.getComponente(), chamado.getCritico() ? "Crítico" : "Alerta",
                        JiraIntegracao.BASE_URL_JIRA + "browse/" + chamado.getChaveJira(),
                        chamado.getChaveJira()));
            } else {
                if (!chamado.getCritico() && contador.maxDisco > 90) {
                    chamado.setCritico(true);
                    chamado.setUltimaMensagemSlack(LocalDateTime.now());

                    SlackIntegracao.enviarMensagem("""
                            Uso de Disco atingiu nível Crítico:
                            Chamado: <%s|%s>
                            Estado: %s
                            """.formatted(JiraIntegracao.BASE_URL_JIRA + "browse/" + chamado.getChaveJira(),
                            chamado.getChaveJira(), chamado.getStatus()));
                    ChamadoDao.atualizarChamado(chamado);
                }
            }
        }
    }

    public static void atualizarBancoSlack(Servidor servidor) throws IOException, InterruptedException {
        List<Chamado> chamadosAbertos = ChamadoDao.listarChamadosAbertosDoServidor(servidor);

        for (Chamado chamado : chamadosAbertos) {
            JSONObject response = JiraIntegracao.getRecurso("issue/" + chamado.getChaveJira());
            if (response == null) {

            } else {
                JSONObject campos = (JSONObject) response.get("fields");
                String statusJira = ((JSONObject) campos.get("status")).get("name").toString();
                if (campos.get("resolution").equals(JSONObject.NULL)) {


                    if (chamado.getStatus().equals(statusJira)) {
                        if (chamado.getUltimaMensagemSlack().plusDays(1).isBefore(LocalDateTime.now())) {
                            SlackIntegracao.enviarMensagem("""
                                    Chamado <%s|%s> não teve seu estado alterado desde a última atualização:
                                    Estado: %s
                                    Última atualização: %s                                    
                                    """.formatted(JiraIntegracao.BASE_URL_JIRA + "browse/" + chamado.getChaveJira(),
                                            chamado.getChaveJira(), chamado.getStatus(), chamado.getUltimaMensagemSlack()
                                            .truncatedTo(ChronoUnit.SECONDS)));
                            chamado.setUltimaMensagemSlack(LocalDateTime.now());
                            ChamadoDao.atualizarChamado(chamado);
                        }
                    } else {
                        SlackIntegracao.enviarMensagem("""
                                    Chamado <%s|%s> mudou de estado:
                                    Estado anterior: %s
                                    Estado atual: %s                                  
                                    """.formatted(JiraIntegracao.BASE_URL_JIRA + "browse/" + chamado.getChaveJira(),
                                chamado.getChaveJira(), chamado.getStatus(), statusJira));
                        chamado.setStatus(statusJira);
                        chamado.setUltimaMensagemSlack(LocalDateTime.now());
                        ChamadoDao.atualizarChamado(chamado);
                    }
                } else {
                    JSONObject resolucao = (JSONObject) campos.get("resolution");
                    chamado.setEncerrado(true);
                    chamado.setUltimaMensagemSlack(LocalDateTime.now());
                    chamado.setStatus(statusJira);
                    ChamadoDao.atualizarChamado(chamado);
                    SlackIntegracao.enviarMensagem("""
                            Chamado <%s|%s> foi encerrado.
                            Motivo: %s
                            """.formatted(JiraIntegracao.BASE_URL_JIRA + "browse/" + chamado.getChaveJira(),
                            chamado.getChaveJira(), resolucao.get("description")));
                }
            }
        }
    }



    private static class ContadorChamados {
        private int alertasCpu;
        private int alertasCpuTemp;
        private int alertasRam;
        private int alertasDisco;
        private double maxCpu;
        private double maxCpuTemp;
        private double maxRam;
        private double maxDisco;

        private void popularDados(List<DadosServidor> ultimosDados) {
            for (DadosServidor dado : ultimosDados) {
                for (Componente componente : dado.getComponentes()) {
                    if (componente instanceof Cpu) {
                        Cpu cpu = (Cpu) componente;
                        if (cpu.getUso() > 70) {
                            this.alertasCpu++;
                            if (cpu.getUso() > this.maxCpu){
                                this.maxCpu = cpu.getUso();
                            }
                        }

                        if (cpu.getTemperatura() > 80) {
                            this.alertasCpuTemp++;
                            if (cpu.getTemperatura() > this.maxCpuTemp){
                                this.maxCpuTemp = cpu.getTemperatura();
                            }
                        }
                    } else if (componente instanceof Memoria) {
                        Memoria ram = (Memoria) componente;
                        if (ram.getUso() > 70) {
                            this.alertasRam++;
                            if (ram.getUso() > this.maxRam){
                                this.maxRam = ram.getUso();
                            }
                        }
                    } else if (componente instanceof Disco) {
                        Disco disco = (Disco) componente;
                        if (disco.getUso() > 70) {
                            this.alertasDisco++;
                            if (disco.getUso() > this.maxDisco){
                                this.maxDisco = disco.getUso();
                            }
                        }
                    }
                }
            }
        }

        @Override
        public String toString() {
            return "ContadorChamados{" +
                    "alertasCpu=" + alertasCpu +
                    ", alertasCpuTemp=" + alertasCpuTemp +
                    ", alertasRam=" + alertasRam +
                    ", alertasDisco=" + alertasDisco +
                    ", maxCpu=" + maxCpu +
                    ", maxCpuTemp=" + maxCpuTemp +
                    ", maxRam=" + maxRam +
                    ", maxDisco=" + maxDisco +
                    '}';
        }
    }
}
