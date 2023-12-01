package school.sptech.utils;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import school.sptech.banco.dao.ComponenteDao;
import school.sptech.banco.dao.DadosServidorDao;
import school.sptech.banco.dao.ServidorDao;
import school.sptech.model.DadosServidor;
import school.sptech.model.ProcessoServidor;
import school.sptech.model.Servidor;
import school.sptech.model.componentes.Componente;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

public class ColetaDadosUtils {
    public static final Looca LOOCA = new Looca();

    public static Servidor obterDadosServidor() {
        String hostName = LOOCA.getRede().getParametros().getHostName();
        String mac = null;
        for (RedeInterface redeInterface : LOOCA.getRede().getGrupoDeInterfaces().getInterfaces()) {
            if (redeInterface.getEnderecoMac().getBytes()[1] % 2 == 0) {
                mac = redeInterface.getEnderecoMac();

                mac = String.join("", mac.split(":"));
                break;
            }
        }

        Servidor servidorBuscado = ServidorDao.buscarServidorPorMac(mac);
        if (servidorBuscado == null) {
            String nomeOs = LOOCA.getSistema().getSistemaOperacional();
            String modelo = LOOCA.getSistema().getFabricante();
            Servidor servidor = new Servidor(modelo, hostName, mac, "Monitoramento de Veículos", nomeOs);
            ServidorDao.inserirServidor(servidor);
            servidor = ServidorDao.buscarServidorPorHostName(hostName);
            ComponenteDao.inserirComponentesServidor(servidor);
            return servidor;
        } else {
            return servidorBuscado;
        }
    }

    @SuppressWarnings({"oshi.util.platform.windows.WmiQueryHandler"})
    public static void monitorarDados(Servidor servidor) {

        List<Componente> componentes = ComponenteDao.buscarComponentesPorServidor(servidor);

        DadosServidor dado = new DadosServidor(componentes, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), servidor.getIdServidor());

        dado.registrarDados();

        servidor.adicionarDado(dado);
        dado = servidor.getUltimoDadosServidor();



        List<Processo> processosLooca = LOOCA.getGrupoDeProcessos().getProcessos();

        for (Processo processoAtual : processosLooca) {
            ProcessoServidor processoServidor = new ProcessoServidor(processoAtual.getNome(), dado.getIdDadosServidor());
            dado.adicionarProcesso(processoServidor);
        }

        // return servidor;
    }

    public static String exibirResumo(Servidor servidor) {
        Map<String, Object> listaResumo = DadosServidorDao.buscarResumoPorServidor(servidor.getIdServidor());

        return """
                %s
                +-------------------------------+
                | Dado  | Min   | Méd   | Máx   |
                +-------------------------------+
                | CPU   | %5.1f | %5.1f | %5.1f |
                | RAM   | %5.1f | %5.1f | %5.1f |
                | Disco | %5.1f | %5.1f | %5.1f |
                +-------------------------------+
                """.formatted(servidor.getHostname(), listaResumo.get("minCpu"), listaResumo.get("avgCpu"), listaResumo.get("maxCpu"),
                listaResumo.get("minRam"), listaResumo.get("avgRam"), listaResumo.get("maxRam"),
                listaResumo.get("minDisco"), listaResumo.get("avgDisco"), listaResumo.get("maxDisco"));
    }
}
