package school.sptech.banco.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import school.sptech.model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRowMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer id = resultSet.getInt("idUsuario");
        String nome = resultSet.getString("nome");
        String email = resultSet.getString("email");
        String senha = resultSet.getString("senha");
        String cpf = resultSet.getString("cpf");
        String foto = resultSet.getString("foto");
        Integer nivelAcesso = resultSet.getInt("nivelAcesso");
        Usuario usuario = new Usuario(id, nome, email, senha, cpf, foto, nivelAcesso);
        return usuario;
    }
}
