package com.cts.dao;

import com.cts.entity.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by fy on 2017/6/4.
 */
@Repository
public class FutureDaoImpl implements FutureDao{
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    class FutureRowMapper implements RowMapper<Future>{

        public Future mapRow(ResultSet resultSet, int i) throws SQLException {
            Future future = new Future();
            future.setId(resultSet.getLong("id"));
            future.setName(resultSet.getString("name"));
            future.setPeriod(resultSet.getString("period"));
            return future;
        }
    }

    public List<Future> getFuturesByName(String name) {
        String sql = "select * from future where name = :name";
        Map<String, String> namedParameters = Collections.singletonMap("name", name);
        List<Future> futures = jdbcTemplate.query(sql, namedParameters, new FutureRowMapper());
        return futures;
    }

    public String getFutureById(Long id) {
        String sql = "select * from future where id = :id";
        Map<String, Long> namedParameters = Collections.singletonMap("id", id);
        Future future = jdbcTemplate.queryForObject(sql, namedParameters, new FutureRowMapper());
        return future.getName()+"-"+future.getPeriod();
    }
}
