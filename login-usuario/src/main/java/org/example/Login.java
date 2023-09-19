package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Login {

    private List<String> nome = new ArrayList<>();
    private List<String> senha = new ArrayList<>();
    private Boolean continuar = true;

    Scanner leitor = new Scanner(System.in);

    public String adicionarUsuario(Usuario usuario) {
        nome.add(usuario.getNome());
        senha.add(usuario.getSenha());

        return  "Usuario adicionado com sucesso";
    }

        public void mensagem(Usuario nome){

            System.out.printf("""
                ===== Bem vindo %s =====
                -----------------------------------
                """, nome.getNome());

        while (continuar) {

            System.out.printf("""
                1 - ir para estatisticas
                2 - sair da aplicação
               -----------------------------------
                
                """, nome.getNome());
            Integer resposta = leitor.nextInt();

            if (resposta == 1){
                graphcar();
            } else if (resposta == 2) {
                continuar = false;
            } else {
                System.out.printf("""
                        ===== Insira um valor válido =====
                        1 - ir para estatisticas
                        2 - sair da aplicação
                        -----------------------------------
                        """);
                resposta = leitor.nextInt();

                if (resposta == 2){
                    continuar = false;
                }
            }
        }

    }


    public  void graphcar(){

        Integer sorteio = ThreadLocalRandom.current().nextInt(1,4);

        if (sorteio == 1){
            System.out.printf("""
                -----------------------------------
                CPU - 50%%
                RAM - 75%%
                GPU - 90%% 
                -----------------------------------
                       Atenção ao uso de GPU
                -----------------------------------
                """);
        } else if (sorteio == 2) {
            System.out.printf("""
                -----------------------------------
                CPU - 50%%
                RAM - 95%%
                GPU - 60%% 
                -----------------------------------
                       Atenção ao uso de RAM
                -----------------------------------
                """);
        } else if (sorteio == 3) {
            System.out.printf("""
                -----------------------------------
                CPU - 90%%
                RAM - 75%%
                GPU - 60%% 
                -----------------------------------
                       Atenção ao uso de CPU
                -----------------------------------
                """);
        } else {
            System.out.printf("""
                -----------------------------------
                CPU - 50%%
                RAM - 75%%
                GPU - 70%% 
                -----------------------------------
                 Tudo certo, tenha uma boa viagem
                -----------------------------------
                """);
        }

    }

    public void login(Usuario usuario){

        System.out.println("Insira seu nome de usuário:");
        String nomeUsuario = leitor.nextLine();

        System.out.println("Insira sua senha");
        String senhaUsuario = leitor.nextLine();

        for (int i = 0; i < nome.size(); i++) {

            if (nomeUsuario.equals(nome.get(i)) && senhaUsuario.equals(senha.get(i))){

                mensagem(usuario);
            } else {

                System.out.println("nome ou senha inválidos");
            }
        }

    }

}
