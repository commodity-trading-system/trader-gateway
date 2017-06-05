package com.cts.service;

import com.cts.dao.OrdInfDao;
import com.cts.entity.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import quickfix.*;

import javax.annotation.Resource;


/**
 * Created by fy on 2017/5/31.
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService{
    @Resource
    private OrdInfDao ordInfDao;

    @Async
    public void iceBurg(Order order){
        // 剩余的问题在于大订单的取消（部分取消）以及拆分的策略x
        try {
            Thread.sleep(2000);
            Integer qty = order.getQty();
            Integer sent = 0;
            for(;sent<qty;sent+=200){
                Thread.sleep(2*60*1000);
                Order subOrder = new Order();
                Long tmpId = ordInfDao.insertOrderInfo("unknown", 1l, order.getBrokerName());
                subOrder.setSide(order.getSide());subOrder.setType(Order.OrdType.MARKET);
                subOrder.setFuture(order.getFuture());subOrder.setPrice(order.getPrice());
                subOrder.setQty(200);subOrder.setBrokerName(order.getBrokerName());
                this.sendOrder(subOrder, tmpId);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void sendOrder(Order order, Long tmpId) {
        Message message = new Message();
        message.getHeader().setField(new StringField(8, "FIX.4.4"));
        message.getHeader().setField(new StringField(49, "TraderGateway"));
        message.getHeader().setField(new StringField(56, order.getBrokerName()));
        message.getHeader().setField(new CharField(35, 'F'));
        message.setField(new IntField(16, order.getType()));
        message.setField(new StringField(11, order.getFuture().toString()));
        message.setField(new IntField(13, order.getQty()));
        message.setField(new IntField(12, 1));
        message.setField(new DoubleField(14, order.getPrice()));
        message.setField(new IntField(15, order.getSide()));
        message.setField(new StringField(17, "order id"));
        message.setField(new IntField(18, tmpId.intValue()));
        try {
            Session.sendToTarget(message);
        } catch (SessionNotFound sessionNotFound) {
            sessionNotFound.printStackTrace();
        }
    }

    public String getOrderId(Long id) {
        return ordInfDao.getOrderId(id);
    }

    public String getBrokerName(String orderId) {
        return ordInfDao.getBrokerName(orderId);
    }

    public Long insertOrder(Long traderId, String brokerName) {
        return ordInfDao.insertOrderInfo("unknown", traderId, brokerName);
    }

    public void updateOrderId(Long id, String orderId) {
        ordInfDao.updateOrderId(id, orderId);
    }
}
