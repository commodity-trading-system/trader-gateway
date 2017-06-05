package com.cts.service;

import com.cts.entity.Order;

/**
 * Created by fy on 2017/6/3.
 */
public interface OrderService {
    void iceBurg(Order order);
    void sendOrder(Order order, Long tmpId);
    String getOrderId(Long id);
    String getBrokerName(String orderId);
    Long insertOrder(Long traderId, String brokerName);
    void updateOrderId(Long id, String orderId);
}
