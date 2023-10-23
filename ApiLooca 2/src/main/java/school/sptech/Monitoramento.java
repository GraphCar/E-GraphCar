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
    public void mensagemMonitorar() {
        Scanner leitor = new Scanner(System.in);
        System.out.println("""
               +-------------------------+
               |      Monitoramento      |
               +-------------------------+
               | 1) CPU                  |
               | 2) Memória              |
               | 3) Disco                |
               | 4) Temperatura          |
               | 5) Todos                |
               | 6) Listar Processos     |
               | 7) Sair                 |
               +-------------------------+
                """);

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

            System.out.println("CPU: " + cpuFormatted + " %");
            System.out.println("RAM: " + ramFormatted + " GB");
            System.out.println("RAM: " + discoFormatted + " / " + new DecimalFormat("#.##").format(volumes.get(0).getTotal() / 1024.0 / 1024.0 / 1024.0) + " GB");
            System.out.println("Temperatura: " + temperaturaFormatted + " C°");

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
//                con.update("INSERT INTO Processos (idProcessos, nomeProcesso) VALUES (?,?)",processo.getPid(), processo.getNome());
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
