package com.cts.dao;

import com.cts.entity.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Created by fy on 2017/6/3.
 */
@Repository
public class TraderDaoImpl implements TraderDao {
    private NamedParameterJdbcTemplate jdbcTemplate;

    class TraderRowMapper implements RowMapper<Trader>{
        public Trader mapRow(ResultSet resultSet, int i) throws SQLException {
            Trader trader = new Trader();
            trader.setId(resultSet.getLong("id"));
            trader.setName(resultSet.getString("name"));
            trader.setPassword(resultSet.getString("password"));
            trader.setFirmId(resultSet.getLong("firmId"));
            return trader;
        }
    }

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Trader getTraderByName(String name) {
        String sql = "select * from trader where name = :name";
        Map<String, String> namedParameters = Collections.singletonMap("name", name);
        List traders = jdbcTemplate.query(sql, namedParameters, new TraderRowMapper());
        if(traders.isEmpty())
            return null;
        return (Trader) traders.get(0);
    }
}
