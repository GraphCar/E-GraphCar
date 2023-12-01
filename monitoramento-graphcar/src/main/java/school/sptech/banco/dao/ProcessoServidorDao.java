package school.sptech.banco.dao;

import school.sptech.banco.rowmappers.ProcessoServidorRowMapper;
import school.sptech.model.ProcessoServidor;
import school.sptech.utils.DatabaseUtils;

import java.util.List;

public class ProcessoServidorDao {
    public static ProcessoServidor buscarProcessoPorNomeEDadosServidor(Integer fkDadosServidor, String nome) {
//        List<ProcessoServidor> listaProcessos = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().query("SELECT * FROM Processos WHERE fkDadosServidor = ? AND nomeProcesso = ?", new ProcessoServidorRowMapper(), fkDadosServidor, nome);
        List<ProcessoServidor> listaProcessos = DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .query("SELECT * FROM Processos WHERE fkDadosServidor = ? AND nomeProcesso = ?",
                        new ProcessoServidorRowMapper(), fkDadosServidor, nome);
        if (listaProcessos.isEmpty()) {
            return null;
        } else {
            return listaProcessos.get(0);
        }
    }

    public static void inserirProcesso(ProcessoServidor processo) {
        //DatabaseUtils.CONEXOES[1].getConexaoDoBanco().update("INSERT INTO Processos (nomeProcesso, fkDadosServidor) VALUES (?, ?)", processo.getNomeProcesso(), processo.getFkDadosServidor());
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .update("INSERT INTO Processos (nomeProcesso, fkDadosServidor) VALUES (?, ?)",
                        processo.getNomeProcesso(), processo.getFkDadosServidor());
    }
}
