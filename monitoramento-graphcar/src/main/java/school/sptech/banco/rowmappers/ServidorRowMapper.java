package school.sptech.banco.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import school.sptech.model.Servidor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServidorRowMapper implements RowMapper<Servidor> {

    @Override
    public Servidor mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer id = resultSet.getInt("idServidor");
        String modelo = resultSet.getString("modeloServidor");
        String hostname = resultSet.getString("hostname");
        String finalidade = resultSet.getString("finalidadeServidor");
        String so = resultSet.getString("sistemaOperacional");
        String mac = resultSet.getString("mac");

        Servidor servidor = new Servidor(id, modelo, hostname, finalidade, so, mac);
        return servidor;
    }
}
