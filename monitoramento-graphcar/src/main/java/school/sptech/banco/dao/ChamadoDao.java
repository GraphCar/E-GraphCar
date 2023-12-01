package school.sptech.banco.dao;

import school.sptech.banco.rowmappers.ChamadoRowMapper;
import school.sptech.model.Chamado;
import school.sptech.model.Servidor;
import school.sptech.utils.DatabaseUtils;

import java.util.List;

public class ChamadoDao {

    public static List<Chamado> listarChamados() {
        //List<Chamado> chamados = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().query("SELECT * FROM Chamado", new ChamadoRowMapper());
        List<Chamado> chamados = DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .query("SELECT * FROM Chamado", new ChamadoRowMapper());
        return chamados;
    }

    public static List<Chamado> listarChamadosAbertosDoServidor(Servidor servidor) {
        //List<Chamado> chamados = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().query("SELECT ch.*, co.nomeComponente AS Componente FROM Chamado ch INNER JOIN Componentes co ON ch.fkComponente = co.idComponentes WHERE ch.fkServidor = ? AND ch.encerrado = 0", new ChamadoRowMapper(), servidor.getIdServidor());
        List<Chamado> chamados = DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .query("""
        SELECT ch.*, co.nomeComponente AS Componente 
        FROM Chamado ch INNER JOIN Componentes co ON ch.fkComponente = co.idComponentes 
        WHERE ch.fkServidor = ? AND ch.encerrado = 0;
        """, new ChamadoRowMapper(), servidor.getIdServidor());
        return chamados;
    }

    public static Chamado buscarChamadoAbertoPorServidorEComponente(Servidor servidor, Integer fkComponente) {
        /* List<Chamado> chamados = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().query("""
                SELECT * FROM Chamado 
                    WHERE fkServidor = ? 
                    AND encerrado = 0
                    AND fkComponente = ?
                """, new ChamadoRowMapper(), servidor.getIdServidor(), fkComponente);
         */
        List<Chamado> chamados = DatabaseUtils.CONEXOES[0].getConexaoDoBanco().query("""
                SELECT * FROM Chamado 
                    WHERE fkServidor = ? 
                    AND encerrado = 0
                    AND fkComponente = ?
                """, new ChamadoRowMapper(), servidor.getIdServidor(), fkComponente);
        if (chamados.isEmpty()) {
            return null;
        } else {
            return chamados.get(0);
        }
    }

    public static void inserirChamado(Chamado chamado) {
        int fkComp = 0;
        if (chamado.getComponente() == "CPU") {
            fkComp = 1;
        } else if (chamado.getComponente() == "RAM") {
            fkComp = 2;
        } else {
            fkComp = 3;
        }
        //DatabaseUtils.CONEXOES[1].getConexaoDoBanco().update("INSERT INTO Chamado (fkServidor, fkComponente, chaveJira, status, encerrado, critico, dataAbertura, ultimaMensagemSlack) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", chamado.getFkServidor(), fkComp, chamado.getChaveJira(), chamado.getStatus(), chamado.getEncerrado(), chamado.getCritico(), chamado.getDataAbertura(), chamado.getUltimaMensagemSlack());
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
    INSERT INTO Chamado (fkServidor, fkComponente, chaveJira, status, encerrado, critico, dataAbertura, ultimaMensagemSlack) 
    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """, chamado.getFkServidor(), fkComp, chamado.getChaveJira(), chamado.getStatus(), chamado.getEncerrado(), chamado.getCritico(), chamado.getDataAbertura(), chamado.getUltimaMensagemSlack());

    }

    public static void atualizarChamado(Chamado chamado) {
//        DatabaseUtils.CONEXOES[1].getConexaoDoBanco().update("""
//            UPDATE Chamado SET status = ?, encerrado = ?, critico = ?, ultimaMensagemSlack = ? WHERE idChamado = ?
//            """, chamado.getStatus(), chamado.getEncerrado(), chamado.getCritico(), chamado.getUltimaMensagemSlack(), chamado.getIdChamado());
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
            UPDATE Chamado SET status = ?, encerrado = ?, critico = ?, 
                ultimaMensagemSlack = ? WHERE idChamado = ?
            """, chamado.getStatus(), chamado.getEncerrado(), chamado.getCritico(), chamado.getUltimaMensagemSlack(), chamado.getIdChamado());

    }

    public static void apagarChamado(Chamado chamado) {
        //DatabaseUtils.CONEXOES[1].getConexaoDoBanco().update("DELETE FROM Chamado WHERE id = ?", chamado.getIdChamado());
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
    DELETE FROM Chamado WHERE id = ?
    """, chamado.getIdChamado());
    }
}
