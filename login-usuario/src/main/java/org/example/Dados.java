package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Scanner;

public class Dados {
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConnection();
    Looca looca = new Looca();
    Scanner leitor = new Scanner(System.in);

    public void setup(){
        System.out.println("Digite o hostname da sua máquina!");
        String hostname; hostname = leitor.nextLine();
        looca.getSistema();

    }
}
