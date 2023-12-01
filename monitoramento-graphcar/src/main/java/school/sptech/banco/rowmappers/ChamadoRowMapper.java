package school.sptech.banco.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import school.sptech.model.Chamado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ChamadoRowMapper implements RowMapper<Chamado> {


    @Override
    public Chamado mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer id = resultSet.getInt("idChamado");
        Integer fkServidor = resultSet.getInt("fkServidor");
        String nomeComponente;
        switch (resultSet.getInt("fkComponente")) {
            case 1:
                nomeComponente = "CPU";
                break;
            case 2:
                nomeComponente = "RAM";
                break;
            case 3:
                nomeComponente = "Disco";
                break;
            default:
                nomeComponente = null;
        }


        String chaveJira = resultSet.getString("chaveJira");
        String status = resultSet.getString("status");
        Boolean encerrado = resultSet.getBoolean("encerrado");

        Boolean critico = resultSet.getBoolean("critico");
        LocalDateTime dataAbertura = resultSet.getTimestamp("dataAbertura").toLocalDateTime();
        LocalDateTime ultimaMensagemSlack = resultSet.getTimestamp("ultimaMensagemSlack").toLocalDateTime();

        Chamado chamado = new Chamado(id, fkServidor, nomeComponente, chaveJira, status, encerrado, critico, dataAbertura, ultimaMensagemSlack);
        return chamado;
    }
}
