package org.example;

import java.util.ArrayList;

public class Teste {
    public static void main(String[] args) {

        Usuario lucas = new Usuario("lucas","lukinhas2704");
        Login entrar = new Login();

        entrar.adicionarUsuario(lucas);

        entrar.login(lucas);
    }
}