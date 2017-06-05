package com.cts.dao;

import com.cts.entity.OrdInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fy on 2017/6/4.
 */
@Repository
public class OrdInfDaoImpl implements OrdInfDao{
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertActor;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("ordInf")
                .usingGeneratedKeyColumns("id");
    }

    class OrdInfRowMapper implements RowMapper<OrdInf>{
        public OrdInf mapRow(ResultSet resultSet, int i) throws SQLException {
            OrdInf ordInf = new OrdInf();
            ordInf.setOrderId(resultSet.getString("orderId"));
            ordInf.setTraderId(resultSet.getLong("traderId"));
            ordInf.setBrokerName(resultSet.getString("brokerName"));
            return ordInf;
        }
    }

    public Long insertOrderInfo(String orderId, Long traderId, String brokerName) {
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("orderId", orderId);
        parameters.put("traderId", traderId);
        Number newId = insertActor.executeAndReturnKey(parameters);
        return Long.valueOf(newId.longValue());
    }

    public String getOrderId(Long id){
        String sql = "select orderId from ordInf where id = :id";
        Map<String, String> namedParameters = Collections.singletonMap("id", id.toString());
        String orderId =  this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
        return orderId;
    }

    public String getBrokerName(String orderId) {
        String sql = "select brokerName from ordInf where orderId = :orderId";
        Map<String, String> namedParameters = Collections.singletonMap("orderId", orderId);
        String brokerName =  this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
        return brokerName;
    }

    public void updateOrderId(Long id, String orderId) {
        this.jdbcTemplate.update("update ordInf set orderId = ? where id = ?", orderId, id);
    }
}
