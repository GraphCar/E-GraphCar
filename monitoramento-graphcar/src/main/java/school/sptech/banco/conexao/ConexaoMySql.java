package school.sptech.banco.conexao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoMySql extends Conexao {
    public ConexaoMySql() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/GraphCar");
        dataSource.setUsername("GraphUser");
        dataSource.setPassword("Graph2023");
        this.conexaoDoBanco = new JdbcTemplate(dataSource);
    }
}
