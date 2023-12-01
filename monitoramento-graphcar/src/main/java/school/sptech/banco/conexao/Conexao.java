package school.sptech.banco.conexao;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class Conexao {
    protected JdbcTemplate conexaoDoBanco;

    public JdbcTemplate getConexaoDoBanco() {
        return this.conexaoDoBanco;
    }
}
