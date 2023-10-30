package school.sptech;


import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConecaoDoBanco();
//
//    Looca looca1 = new Looca();
//
//    Memoria memoria = looca1.getMemoria();
//
//    Processador processador = looca1.getProcessador();
//
//    DiscoGrupo discoGrupo = looca1.getDisco();
//
//    List<Volume> volumes = discoGrupo.getVolumes();
//
//    System.out.println(memoria.getEmUso());
//    System.out.println(processador.getUso());
//    System.out.println(volumes.get(0).getDisponivel());
//
        Login login = new Login(new JdbcTemplate());
        login.mensagem();

    }
}