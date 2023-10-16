package org.example;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {
    private JdbcTemplate connection;

    public Conexao() {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("org.h2.Driver");

        dataSource.setUrl("jdbc:mysql://localhost:3306/GraphCar");

        dataSource.setUsername("GraphUser");

        dataSource.setPassword("Graph2023");

        this.connection = new JdbcTemplate(dataSource);

    }

    public JdbcTemplate getConnection() {return connection;}
}
