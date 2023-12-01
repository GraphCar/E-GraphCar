package school.sptech.banco.conexao;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoSqlServer extends Conexao {

    public ConexaoSqlServer() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setUser("sa");
        dataSource.setPassword("urubu100");
        dataSource.setServerName("54.84.147.78");
        dataSource.setDatabaseName("GraphCar");
        dataSource.setTrustServerCertificate(true);
        this.conexaoDoBanco = new JdbcTemplate(dataSource);
    }
}
