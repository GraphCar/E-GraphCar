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
    public String ANSI_YELLOW = "\u001B[33m";
    public String ANSI_BLUE = "\u001B[34m";
    public String ANSI_WHITE = "\u001B[37m";

    Conexao conexao = new Conexao();
//    Statement statement = connection.createStatement();
    JdbcTemplate con = conexao.getConecaoDoBanco();

    public Login(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void mensagem() {
        Scanner leitor = new Scanner(System.in);
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
