package school.sptech.banco.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import school.sptech.model.DadosServidor;
import school.sptech.model.componentes.Componente;
import school.sptech.model.componentes.Cpu;
import school.sptech.model.componentes.Disco;
import school.sptech.model.componentes.Memoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DadosServidorRowMapper implements RowMapper<DadosServidor> {

    @Override
    public DadosServidor mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("idDadosServidor");
        List<Componente> componentes = new ArrayList<>();
        Double cpuUso = resultSet.getDouble("cpuUso");
        if (cpuUso != 0) {
            componentes.add(new Cpu(cpuUso, resultSet.getDouble("cpuTemperatura")));
        }

        Double memoria = resultSet.getDouble("memoria");
        if (memoria != 0) {
            componentes.add(new Memoria(memoria));
        }

        Double disco = resultSet.getDouble("disco");
        if (disco != 0) {
            componentes.add(new Disco(disco));
        }

        LocalDateTime dataHora = resultSet.getTimestamp("dateDado").toLocalDateTime();
        Integer fkServidor = resultSet.getInt("fkServidor");

        DadosServidor dado = new DadosServidor(id, componentes, dataHora, fkServidor);

        return dado;
    }
}
