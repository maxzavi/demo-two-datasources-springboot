package pe.maxz.demotwodatasources.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class MySQLRepository {
    @Autowired
    @Qualifier("mysqllocalJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    public void test(){
        String query = "SELECT now()";
        try (
            @SuppressWarnings("null")
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
        ) {
            var rs = pst.executeQuery();
            if(rs.next()){
                log.info("Now in MySql:  {}", rs.getString(1));
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

}
