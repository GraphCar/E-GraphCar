package school.sptech.utils;

import school.sptech.banco.conexao.Conexao;
import school.sptech.banco.conexao.ConexaoSqlServer;

public class DatabaseUtils {
    public static final Conexao[] CONEXOES = new Conexao[]{new ConexaoSqlServer()};
}