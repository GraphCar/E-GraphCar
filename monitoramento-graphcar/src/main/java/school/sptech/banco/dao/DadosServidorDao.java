package school.sptech.banco.dao;

import school.sptech.banco.rowmappers.DadosServidorRowMapper;
import school.sptech.model.DadosServidor;
import school.sptech.model.componentes.Componente;
import school.sptech.model.componentes.Cpu;
import school.sptech.model.componentes.Disco;
import school.sptech.model.componentes.Memoria;
import school.sptech.utils.DatabaseUtils;

import java.util.List;
import java.util.Map;

public class DadosServidorDao {

    public static DadosServidor buscarDadosServidorPorServidorEDataHora(Integer fkServidor, String dataHora) {
        //List<DadosServidor> listaDados = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().query("SELECT * FROM DadosServidor WHERE fkServidor = ? AND dateDado = ?", new DadosServidorRowMapper(), fkServidor, dataHora);
        List<DadosServidor> listaDados = DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .query("""
        SELECT * FROM DadosServidor WHERE fkServidor = ? AND dateDado = ?
        """, new DadosServidorRowMapper(), fkServidor, dataHora);
        if (listaDados.isEmpty()) {
            return null;
        } else {
            return listaDados.get(0);
        }
    }

    public static Map<String, Object> buscarResumoPorServidor(Integer idServidor) {
//        List<Map<String, Object>> resultados = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().queryForList("""
//                SELECT MAX(cpuUso) AS maxCpu, AVG(cpuUso) AS avgCpu, MIN(cpuUso) AS minCpu,
//                MAX(cpuTemperatura) AS maxCpuTemp, AVG(cpuTemperatura) AS avgCpuTemp, MIN(cpuTemperatura) AS minCpuTemp,
//                MAX(memoria) AS maxRam, AVG(memoria) AS avgRam, MIN(memoria) AS minRam,
//                MAX(disco) AS maxDisco, AVG(disco) AS avgDisco, MIN(disco) AS minDisco
//                FROM DadosServidor WHERE fkServidor = ?;""", idServidor);

        List<Map<String, Object>> resultados = DatabaseUtils.CONEXOES[0].getConexaoDoBanco().queryForList("""
                SELECT MAX(cpuUso) AS maxCpu, AVG(cpuUso) AS avgCpu, MIN(cpuUso) AS minCpu,
                MAX(cpuTemperatura) AS maxCpuTemp, AVG(cpuTemperatura) AS avgCpuTemp, MIN(cpuTemperatura) AS minCpuTemp,
                MAX(memoria) AS maxRam, AVG(memoria) AS avgRam, MIN(memoria) AS minRam,
                MAX(disco) AS maxDisco, AVG(disco) AS avgDisco, MIN(disco) AS minDisco 
                FROM DadosServidor WHERE fkServidor = ?;
                """, idServidor);
        if (resultados.isEmpty()) {
            return null;
        } else {
            return resultados.get(0);
        }
    }

    public static void inserirDadosServidor(DadosServidor dadosServidor) {
        List<Componente> componentes = dadosServidor.getComponentes();
        Double[] dados = new Double[4];

        for (Componente componenteDaVez : componentes) {
            if (componenteDaVez instanceof Cpu) {
                dados[0] = ((Cpu) componenteDaVez).getUso();
                dados[3] = ((Cpu) componenteDaVez).getTemperatura();
            } else if (componenteDaVez instanceof Memoria) {
                dados[1] = ((Memoria) componenteDaVez).getUso();
            } else {
                dados[2] = ((Disco) componenteDaVez).getUso();
            }
        }

        //DatabaseUtils.CONEXOES[1].getConexaoDoBanco().update("INSERT INTO DadosServidor (cpuUso, memoria, disco, cpuTemperatura, dateDado, fkServidor) VALUES (?, ?, ?, ?, ?, ?)", dados[0], dados[1], dados[2], dados[3], dadosServidor.getDateDado(), dadosServidor.getFkServidor());
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
    INSERT INTO DadosServidor (cpuUso, memoria, disco, cpuTemperatura, dateDado, fkServidor) 
    VALUES (?, ?, ?, ?, ?, ?);
    """, dados[0], dados[1], dados[2], dados[3], dadosServidor.getDateDado(), dadosServidor.getFkServidor());
    }
}
