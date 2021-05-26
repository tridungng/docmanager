//package com.project.docmanager.Config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DatabaseConfig {
//
//    @Bean
//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("mysql://b067401cf5ec73:ba7d3d21@eu-cdbr-west-01.cleardb.com/heroku_e516bc03f8eea12?reconnect=true");
//        dataSource.setUsername("b067401cf5ec73");
//        dataSource.setPassword("ba7d3d21");
//        return dataSource;
//    }
//}
//
//package com.project.docmanager.Config;
//
//import com.zaxxer.hikari.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.*;
//import javax.sql.DataSource;
//
//@Configuration
//public class DatabaseConfig {
//
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//
//    @Bean
//    public DataSource dataSource() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(dbUrl);
//        return new HikariDataSource(config);
//    }
//}