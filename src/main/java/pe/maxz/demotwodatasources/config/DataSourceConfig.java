package pe.maxz.demotwodatasources.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
@Configuration
public class DataSourceConfig {
    @Primary
    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource orcaleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqllocalDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysqllocal")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Primary
    @Bean(name = "oracleJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("oracleDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "mysqllocalJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("mysqllocalDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
