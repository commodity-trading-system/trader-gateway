package com.cts.dao;

/**
 * Created by fy on 2017/6/4.
 */
public interface OrdInfDao {
    Long insertOrderInfo(String orderId, Long traderId, String brokerName);
    String getOrderId(Long id);
    String getBrokerName(String orderId);
    void updateOrderId(Long id, String orderId);
}
