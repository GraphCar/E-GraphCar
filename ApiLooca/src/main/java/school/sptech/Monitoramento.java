package school.sptech;

import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Monitoramento {
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConecaoDoBanco();
    DadosServidor dadosServidor = new DadosServidor();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public void mensagemMonitorar() {
        Scanner leitor = new Scanner(System.in);
        System.out.println(ANSI_RED +
                "+-------------------------+" + "\n" +
                "|      " + ANSI_GREEN + "Monitoramento" + ANSI_RED + "      |" + "\n" +
                "+-------------------------+" + "\n" +
                "| " + ANSI_RESET + "1) CPU" + ANSI_RED + "                  |" + "\n" +
                "| " + ANSI_RESET + "2) Memória" + ANSI_RED + "              |" + "\n" +
                "| " + ANSI_RESET + "3) Disco" + ANSI_RED + "                |" + "\n" +
                "| " + ANSI_RESET + "4) Temperatura" + ANSI_RED + "          |" + "\n" +
                "| " + ANSI_RESET + "5) Todos" + ANSI_RED + "                |" + "\n" +
                "| " + ANSI_RESET + "6) Listar Processos" + ANSI_RED + "     |" + "\n" +
                "| " + ANSI_RESET + "7) Sair" + ANSI_RED + "                 |" + "\n" +
                "+-------------------------+" + ANSI_RESET);

        int escolha = leitor.nextInt();

        switch (escolha) {
            case 1:
                monitorarCPU();
                break;
            case 2:
                monitorarRAM();
                break;
            case 3:
                monitorarDisco();
                break;
            case 4:
                monitorarTemperatura();
                break;
            case 5:
                monitorarTodos();
                break;
            case 6:
                monitorarProcesso();
                break;
            case 7:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Escolha inválida. Tente novamente.");
        }
    }

public void monitorarCPU() {
        int i = 0;

        while (i < 5) {

            Looca looca1 = new Looca();
            Processador processador = looca1.getProcessador();

            double cpuUsage = processador.getUso();

            String cpuFormatted = new DecimalFormat("#.##").format(cpuUsage).replace(",", ".");
            String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            System.out.println("CPU: " + cpuFormatted + " %");

            Integer id = con.queryForObject("SELECT idServidor FROM Servidor ORDER BY idServidor DESC LIMIT 1", Integer.class);
            con.update("INSERT INTO DadosServidor (cpuUso, dateDado,fkServidor) VALUES (?,?,?)",cpuFormatted, dataAtual, id);

            i++;

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.mensagemMonitorar();
    }

        public void monitorarRAM(){
            int i = 0;

            while (i < 5) {

                Looca looca1 = new Looca();
                Memoria memoria = looca1.getMemoria();

                double memoriaUsage = memoria.getEmUso();

                String ramFormatted = new DecimalFormat("#.##").format(memoriaUsage / 1024.0 / 1024.0 / 1024.0).replace(",", ".");
                String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                System.out.println("RAM: " + ramFormatted + " GB");

                Integer id = con.queryForObject("SELECT idServidor FROM Servidor ORDER BY idServidor DESC LIMIT 1", Integer.class);
                con.update("INSERT INTO DadosServidor (memoria, dateDado, fkServidor) VALUES (?,?,?)",ramFormatted, dataAtual, id);

                i++;

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        this.mensagemMonitorar();
    }

    public void monitorarDisco(){
        int i = 0;

        while (i < 5) {

            Looca looca1 = new Looca();
            DiscoGrupo discoGrupo = looca1.getDisco();
            List<Volume> volumes = discoGrupo.getVolumes();

            double discoUsage = volumes.get(0).getDisponivel();

            String discoFormatted = new DecimalFormat("#.##").format(discoUsage / 1024.0 / 1024.0 / 1024.0).replace(",", ".");
            String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            System.out.println("RAM: " + discoFormatted + " / " + new DecimalFormat("#.##").format(volumes.get(0).getTotal() / 1024.0 / 1024.0 / 1024.0) + " GB");

            Integer id = con.queryForObject("SELECT idServidor FROM Servidor ORDER BY idServidor DESC LIMIT 1", Integer.class);
            con.update("INSERT INTO DadosServidor (disco, dateDado, fkServidor) VALUES (?,?,?)",discoFormatted, dataAtual,id);

            i++;

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.mensagemMonitorar();
    }

    public void monitorarTemperatura(){
        int i = 0;

        while (i < 5) {

            Looca looca1 = new Looca();
            Temperatura temperatura = new Temperatura();

            double temperaturaUsage = temperatura.getTemperatura();

            String temperaturaFormatted = new DecimalFormat("#.##").format(temperaturaUsage).replace(",", ".");
            String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            System.out.println("Temperatura: " + temperaturaFormatted + " C°");

            Integer id = con.queryForObject("SELECT idServidor FROM Servidor ORDER BY idServidor DESC LIMIT 1", Integer.class);
            con.update("INSERT INTO DadosServidor (cpuTemperatura, dateDado, fkServidor) VALUES (?,?,?)",temperaturaFormatted, dataAtual, id);

            i++;

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.mensagemMonitorar();
    }

    public void monitorarTodos(){
        int i= 0;

        while (i < 5){

            Looca looca1 = new Looca();
            Processador processador = looca1.getProcessador();
            Memoria memoria = looca1.getMemoria();
            DiscoGrupo discoGrupo = looca1.getDisco();
            List<Volume> volumes = discoGrupo.getVolumes();
            Temperatura temperatura = new Temperatura();

            double cpuUsage = processador.getUso();
            double memoriaUsage = memoria.getEmUso();
            double discoUsage = volumes.get(0).getDisponivel();
            double temperaturaUsage = temperatura.getTemperatura();

            String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            String cpuFormatted = new DecimalFormat("#.##").format(cpuUsage).replace(",", ".");
            String ramFormatted = new DecimalFormat("#.##").format(memoriaUsage / 1024.0 / 1024.0 / 1024.0).replace(",", ".");
            String discoFormatted = new DecimalFormat("#.##").format(discoUsage / 1024.0 / 1024.0 / 1024.0).replace(",", ".");
            String temperaturaFormatted = new DecimalFormat("#.##").format(temperaturaUsage).replace(",", ".");
            String discoTotalFormatted = new DecimalFormat("#.##").format(volumes.get(0).getTotal() / 1024.0 / 1024.0 / 1024.0);

            System.out.println("""
                   +------------------------------+
                   | Dados capturados             |
                   +------------------------------+
                   | CPU: %s                    |
                   | RAM: %s GB                 |
                   | Disco: %s / %s GB    |
                   | Temperatura: %s C°            |
                   +------------------------------+
                   """.formatted(cpuFormatted, ramFormatted, discoFormatted, discoTotalFormatted, temperaturaFormatted));

            Integer id = con.queryForObject("SELECT idServidor FROM Servidor ORDER BY idServidor DESC LIMIT 1", Integer.class);
            con.update("INSERT INTO DadosServidor (cpuUso,cpuTemperatura,memoria,disco, dateDado, fkServidor) VALUES (?,?,?,?,?,?)",cpuFormatted,temperaturaFormatted,ramFormatted,discoFormatted, dataAtual, id);

            i++;

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.mensagemMonitorar();

    }


    public void monitorarProcesso(){
        int i = 0;

        while (i < 5) {

            Looca looca = new Looca();
            ProcessoGrupo processoGrupo = new ProcessoGrupo();

            List<Processo> processos = processoGrupo.getProcessos();

            for (Processo processo : processos){
                System.out.println(processo);
                con.update("INSERT INTO Processos (idProcessos, nomeProcesso) VALUES (?,?)",processo.getPid(), processo.getNome());
            }

            i++;

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.mensagemMonitorar();
    }
}
