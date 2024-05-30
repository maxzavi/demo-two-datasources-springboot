# Demo connect two datasource Oracle and MySQl

## MySQL in docker

```cmd
docker run -d -e MYSQL_ROOT_PASSWORD=***** -e MYSQL_DATABASE=db -p3306:3306  mysql
```

Create table:

```sql
CREATE TABLE WmsShipmentMainApi (
shipment_id varchar(45) NOT NULL,
 facility_key varchar(20) DEFAULT NULL,
 shipment_nbr varchar(45) DEFAULT NULL,
 create_ts timestamp DEFAULT NULL,
 mod_ts timestamp DEFAULT NULL,
 upload_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
 shipment_type_id varchar(50) DEFAULT NULL,
 shipment_type_key varchar(50) DEFAULT NULL,
  PRIMARY KEY (shipment_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

select *
#delete
from db.WmsShipmentMainApi;
```

## Config

```yml
spring:
  application:
    name: demotwodatasources
  datasource:
    oracle:
      jdbcurl: jdbc:oracle:thin:@ip:port:sid
      username: ******
      password: ********   
    mysqllocal:
      jdbcurl: jdbc:mysql://localhost:3306/dn
      username: root
      password: ******
logging:
  level:
    pe: INFO
```

# Configuration

```java
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
```

Primary tag, define default datasource:
```java
    @Autowired
    private JdbcTemplate jdbcTemplate;

```

By using other tag with Qualifier

```java
    @Autowired
    @Qualifier("mysqllocalJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

```