package school.sptech.banco.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import school.sptech.model.componentes.Componente;
import school.sptech.model.componentes.Cpu;
import school.sptech.model.componentes.Disco;
import school.sptech.model.componentes.Memoria;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponenteRowMapper implements RowMapper<Componente> {
    @Override
    public Componente mapRow(ResultSet resultSet, int i) throws SQLException {
        String tipo = resultSet.getString("tipo");

        if (tipo.equals("CPU")) {
            Cpu cpu = new Cpu();
            return cpu;
        } else if (tipo.equals("RAM")) {
            Memoria memoria = new Memoria();
            return memoria;
        } else {
            Disco disco = new Disco();
            return disco;
        }
    }
}
