package school.sptech;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Login {

    private JdbcTemplate jdbcTemplate;
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";

    Conexao conexao = new Conexao();
//    Statement statement = connection.createStatement();
    JdbcTemplate con = conexao.getConecaoDoBanco();

    public Login(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void mensagem() {
        Scanner leitor = new Scanner(System.in);
        System.out.println( """
                   .oooooo.                                  oooo          .oooooo.                     \s
                  d8P'  `Y8b                                 `888         d8P'  `Y8b                    \s
                 888           oooo d8b  .oooo.   oo.ooooo.   888 .oo.   888           .oooo.   oooo d8b\s
                 888           `888""8P `P  )88b   888' `88b  888P"Y88b  888          `P  )88b  `888""8P\s
                 888     ooooo  888      .oP"888   888   888  888   888  888           .oP"888   888    \s
                 `88.    .88'   888     d8(  888   888   888  888   888  `88b    ooo  d8(  888   888    \s
                  `Y8bood8P'   d888b    `Y888""8o  888bod8P' o888o o888o  `Y8bood8P'  `Y888""8o d888b   \s
                                                   888                                                  \s
                                                  o888o                                     \s
                """);
        System.out.println(ANSI_RED + "+-------------------------+");
        System.out.println("|      " + ANSI_GREEN + "   Login" + ANSI_RED + "           |");
        System.out.println("+-------------------------+");
        System.out.println("| " + ANSI_RESET + "1) Fazer login" + ANSI_RED + "          |");
        System.out.println("| " + ANSI_RESET + "2) Sair" + ANSI_RED + "                 |");
        System.out.println("+-------------------------+" + ANSI_RESET);

        int escolha = leitor.nextInt();
        if (escolha == 1) {

            System.out.println("Digite seu nome de usuário:");
            String emailUsuario = leitor.next();
            System.out.println("Digite sua senha:");
            String senhaUsuario = leitor.next();

            List<Usuario> usuarios = con.query("SELECT * FROM usuario WHERE email = ? and senha = ?",
                    new BeanPropertyRowMapper<>(Usuario.class), emailUsuario,senhaUsuario);

            if (usuarios.size() == 1) {
                System.out.println("Bem vindo " + emailUsuario + "!! \n");

                Monitoramento monitoramento = new Monitoramento();
                DadosServidor dadosServidor = new DadosServidor();
                dadosServidor.dadosServidor();
                monitoramento.mensagemMonitorar();

            } else {
                System.out.println("Nome de usuário ou senha incorretos. Tente novamente.");
            }
        } else {
            System.out.println("Saindo...");
        }
    }
}
