package org.example;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Login {

    private List<String> nome = new ArrayList<>();
    private List<String> senha = new ArrayList<>();
    private Boolean continuar = true;
    Scanner leitor = new Scanner(System.in);
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConnection();

    Usuario user = new Usuario();
        public void mensagem() {
            Usuario newUsuario = new Usuario();
            Integer escolha;
            System.out.printf("""
                    ===== Bem vindo %s =====
                    -----------------------------------
                    """);
            System.out.println("""
                    1 - Criar usuário
                    2 - Fazer login
                    -----------------------------------
                    """);
            escolha = leitor.nextInt();
            if (escolha == 1) {

                System.out.println("Digite seu nome de usuário");
                newUsuario.setusuarioMonitoramento(leitor.nextLine());
                System.out.println("Digite sua senha");
                newUsuario.setsenhaMonitoramento(leitor.nextLine());
                user.adicionarUsuario(newUsuario);
            } else if (escolha == 2) {
                System.out.println("Digite seu nome de usuário");
                newUsuario.setusuarioMonitoramento(leitor.nextLine());
                System.out.println("Digite sua senha");
                newUsuario.setsenhaMonitoramento(leitor.nextLine());
                login(newUsuario);

            } else {
                System.out.println("Escolha inválida");

            }
        }

    public void login(Usuario usuario){

        System.out.println("Insira seu nome de usuário:");
        String nomeUsuario = leitor.nextLine();

        System.out.println("Insira sua senha");
        String senhaUsuario = leitor.nextLine();

        if(user.usuarioExists(nomeUsuario, senhaUsuario)){

        }
        else if(user.usuarioExists(nomeUsuario, senhaUsuario)){
            

        }
    }

}
