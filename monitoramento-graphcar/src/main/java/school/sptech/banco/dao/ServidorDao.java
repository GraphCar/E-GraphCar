package school.sptech.banco.dao;

import school.sptech.banco.rowmappers.ServidorRowMapper;
import school.sptech.model.Servidor;
import school.sptech.utils.DatabaseUtils;

import java.util.List;

public class ServidorDao {

    public static Boolean existsServidorPorHostName(String hostname) {
//        List<Servidor> listaServidores = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().query("SELECT * FROM Servidor WHERE hostname = ?", new BeanPropertyRowMapper<>(Servidor.class), hostname);
        List<Servidor> listaServidores = DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .query("SELECT * FROM Servidor WHERE hostname = ?",
                        new ServidorRowMapper(), hostname);
        if (listaServidores.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static Servidor buscarServidorPorHostName(String hostname) {
//        List<Servidor> listaServidores = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().query("SELECT * FROM Servidor WHERE hostname = ?", new BeanPropertyRowMapper<>(Servidor.class), hostname);
        List<Servidor> listaServidores = DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .query("SELECT * FROM Servidor WHERE hostname = ?",
                        new ServidorRowMapper(), hostname);
        if (listaServidores.isEmpty()) {
            return null;
        } else {
            return listaServidores.get(0);
        }
    }

    public static Boolean existsServidorPorMac(String mac) {
//        List<Servidor> listaServidores = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().query("SELECT * FROM Servidor WHERE mac = ?", new BeanPropertyRowMapper<>(Servidor.class), mac);
        List<Servidor> listaServidores = DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .query("SELECT * FROM Servidor WHERE mac = ?",
                        new ServidorRowMapper(), mac);
        if (listaServidores.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static Servidor buscarServidorPorMac(String mac) {
//        List<Servidor> listaServidores = DatabaseUtils.CONEXOES[1].getConexaoDoBanco().query("SELECT * FROM Servidor WHERE mac = ?", new BeanPropertyRowMapper<>(Servidor.class), mac);
        List<Servidor> listaServidores = DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .query("SELECT * FROM Servidor WHERE mac = ?",
                        new ServidorRowMapper(), mac);
        if (listaServidores.isEmpty()) {
            return null;
        } else {
            return listaServidores.get(0);
        }
    }

    public static void inserirServidor(Servidor servidor) {
        //DatabaseUtils.CONEXOES[1].getConexaoDoBanco().update("INSERT INTO Servidor (modeloServidor, hostname, mac, finalidadeServidor, sistemaOperacional, dataCriacao) VALUES (?, ?, ?, ?, ?, now())", servidor.getModeloServidor(), servidor.getHostname(), servidor.getMac(), servidor.getFinalidadeServidor(), servidor.getSistemaOperacional());
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .update("""
                        INSERT INTO Servidor (modeloServidor, hostname, mac, finalidadeServidor, sistemaOperacional, dataCriacao)
                        VALUES (?, ?, ?, ?, ?, GETDATE())
                        """, servidor.getModeloServidor(), servidor.getHostname(), servidor.getMac(), servidor.getFinalidadeServidor(), servidor.getSistemaOperacional());
    }

    public static void atualizarServidor(Servidor servidor) {
//        DatabaseUtils.CONEXOES[1].getConexaoDoBanco().update("UPDATE Servidor SET modeloServidor = ?, hostname = ?, mac = ?, finalidadeServidor = ?, sistemaOperacional = ? WHERE id = ?", servidor.getModeloServidor(), servidor.getHostname(), servidor.getMac(), servidor.getFinalidadeServidor(), servidor.getSistemaOperacional(), servidor.getIdServidor());
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco().update("""
    UPDATE Servidor SET modeloServidor = ?, hostname = ?, mac = ?, 
    finalidadeServidor = ?, sistemaOperacional = ? WHERE id = ?
    """, servidor.getModeloServidor(), servidor.getHostname(), servidor.getMac(), servidor.getFinalidadeServidor(), servidor.getSistemaOperacional(), servidor.getIdServidor());
    }

    public static void apagarServidor(Servidor servidor) {
//        DatabaseUtils.CONEXOES[1].getConexaoDoBanco().update("DELETE FROM Servidor WHERE id = ?", servidor.getIdServidor());
        DatabaseUtils.CONEXOES[0].getConexaoDoBanco()
                .update("DELETE FROM Servidor WHERE id = ?", servidor.getIdServidor());
    }


}
