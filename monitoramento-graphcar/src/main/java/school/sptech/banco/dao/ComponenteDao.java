package school.sptech.banco.dao;

import school.sptech.banco.rowmappers.ComponenteRowMapper;
import school.sptech.model.Servidor;
import school.sptech.model.componentes.Componente;
import school.sptech.utils.DatabaseUtils;

import java.util.List;

public class ComponenteDao {
    public static List<Componente> buscarComponentesPorServidor(Servidor servidor) {
//        List<Componente> listaComponentes = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().query("""
//                SELECT c.NomeComponente AS tipo FROM Componentes c INNER JOIN
//                    ModeloComponente mc ON mc.fkComponente = c.idComponentes WHERE mc.fkServidor = ?
//                """, new ComponenteRowMapper(), servidor.getIdServidor());
        List<Componente> listaComponentes = DatabaseUtils.CONEXOES[0].getConexaoDoBanco().query("""
                SELECT c.NomeComponente AS tipo FROM Componentes c INNER JOIN 
                    ModeloComponente mc ON mc.fkComponente = c.idComponentes WHERE mc.fkServidor = ?
                """, new ComponenteRowMapper(), servidor.getIdServidor());
        return listaComponentes;
    }

    public static void inserirComponentesServidor(Servidor servidor) {
//        DatabaseUtils.CONEXOES[1].getConexaoDoBanco().update("""
//                INSERT INTO ModeloComponente (fkComponente, fkServidor) VALUES
//                    (1, ?), (2, ?), (3, ?);
//                """, servidor.getIdServidor(), servidor.getIdServidor(), servidor.getIdServidor());
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
                INSERT INTO ModeloComponente (fkComponente, fkServidor) VALUES
                    (1, ?), (2, ?), (3, ?);
                """, servidor.getIdServidor(), servidor.getIdServidor(), servidor.getIdServidor());
    }
}
