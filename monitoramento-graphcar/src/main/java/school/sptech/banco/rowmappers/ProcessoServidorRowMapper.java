package school.sptech.banco.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import school.sptech.model.ProcessoServidor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcessoServidorRowMapper implements RowMapper<ProcessoServidor> {

    @Override
    public ProcessoServidor mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer id = resultSet.getInt("idProcessos");
        String nome = resultSet.getString("nomeProcesso");
        Integer fkDados = resultSet.getInt("fkDadosServidor");
        ProcessoServidor processo = new ProcessoServidor(id, nome, fkDados);
        return processo;
    }
}
