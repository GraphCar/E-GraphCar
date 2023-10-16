package org.example;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConnection();
    private Integer idUsuario;
    private String usuarioMonitoramento;
    private String senhaMonitoramento;

    public String getusuarioMonitoramento() {
        return usuarioMonitoramento;
    }

    public void setusuarioMonitoramento(String nome) {
        this.usuarioMonitoramento = nome;
    }

    public String getsenhaMonitoramento() {
        return senhaMonitoramento;
    }

    public void setsenhaMonitoramento(String senha) {
        this.senhaMonitoramento = senhaMonitoramento;
    }

    public Boolean usuarioExists(String usuarioMonitoramento,String senhaMonitoramento ){
        Boolean usuarioCadastrado = false;
        List <Usuario> usuario = con.query("SELECT * FROM LoginMonitoramento WHERE usuarioMonitoramento = ? and senhaMonitoramento = ? " ,
                new BeanPropertyRowMapper<>(Usuario.class), usuarioMonitoramento, senhaMonitoramento);
        if(usuario.isEmpty()){
            return usuarioCadastrado;
        }
        else{
            return !usuarioCadastrado;
        }
    }

    public void adicionarUsuario(Usuario usuario) {
        con.update("INSERT INTO usuario (nome,senha) VALUES (%s , %s) ", usuarioMonitoramento,senhaMonitoramento);
        System.out.println("Usuario adicionado com sucesso");
    }


}
