package school.sptech;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class Conexao {

    private JdbcTemplate conecaoDoBanco;

    public Conexao(){
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setDatabaseName("GraphCar");
        mysqlDataSource.setUser("GraphUser");
        mysqlDataSource.setPassword("Graph2023");

        conecaoDoBanco = new JdbcTemplate(mysqlDataSource);

    }

    public JdbcTemplate getConecaoDoBanco(){
        return conecaoDoBanco;
    }
}
