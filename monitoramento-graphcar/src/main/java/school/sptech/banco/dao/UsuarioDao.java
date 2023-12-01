package school.sptech.banco.dao;

import school.sptech.banco.rowmappers.UsuarioRowMapper;
import school.sptech.model.Usuario;
import school.sptech.utils.DatabaseUtils;

import java.util.List;

public class UsuarioDao {

    public static List<Usuario> listarUsuarios() {
//        List<Usuario> usuarios = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().query("SELECT * FROM Usuario;", new UsuarioRowMapper());
        List<Usuario> usuarios = DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .query("SELECT * FROM Usuario;", new UsuarioRowMapper());
        return usuarios;
    }

    public static List<Usuario> validarLogin(String nome, String senha) {
//        List<Usuario> usuarioLogado = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().query("SELECT * FROM usuario WHERE nome = ? AND senha = ?;", new BeanPropertyRowMapper<>(Usuario.class), nome, senha);
        List<Usuario> usuarioLogado = DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .query("SELECT * FROM usuario WHERE nome = ? AND senha = ?;",
                        new UsuarioRowMapper(), nome, senha);
        return usuarioLogado;
    }

    public static void inserirUsuario(Usuario usuario) {
//        DatabaseUtils.CONEXOES[1].getConexaoDoBanco().update("INSERT INTO usuario (nome, email, senha, cpf, foto, nivelAcesso) VALUES (?, ?, ?, ?, ?, ?)", usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCpf(), usuario.getFoto(), usuario.getNivelAcesso());
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
    INSERT INTO usuario (nome, email, senha, cpf, foto, nivelAcesso) VALUES (?, ?, ?, ?, ?, ?)
    """, usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCpf(), usuario.getFoto(), usuario.getNivelAcesso());
    }

    public static void atualizarUsuario(Usuario usuario) {
//        DatabaseUtils.CONEXOES[1].getConexaoDoBanco().update("UPDATE usuario SET nome = ?, email = ?, senha = ?, cpf = ?, foto = ?, nivelAcesso = ? WHERE id = ?", usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCpf(), usuario.getFoto(), usuario.getNivelAcesso(), usuario.getIdUsuario());
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
    UPDATE usuario SET nome = ?, email = ?, senha = ?, cpf = ?, foto = ?, nivelAcesso = ? WHERE id = ?
    """, usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCpf(), usuario.getFoto(), usuario.getNivelAcesso(), usuario.getIdUsuario());
    }

    public static void apagarUsuario(Usuario usuario) {
//        DatabaseUtils.CONEXOES[1].getConexaoDoBanco().update("DELETE FROM usuario WHERE id = ?", usuario.getIdUsuario());
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
    DELETE FROM usuario WHERE id = ?
    """, usuario.getIdUsuario());
    }

}
