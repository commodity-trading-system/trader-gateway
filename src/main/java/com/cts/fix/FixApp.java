package com.cts.fix;

import com.cts.service.OrderService;
import com.cts.util.SpringContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import quickfix.*;
import quickfix.Application;
import quickfix.fix44.MessageCracker;
import quickfix.fix44.NewOrderSingle;

import javax.annotation.Resource;


/**
 * Created by fy on 2017/5/30.
 */

public class FixApp extends MessageCracker implements Application{
    public void onCreate(SessionID sessionId) {
        System.out.println(sessionId +" on create");
    }

    public void onLogon(SessionID sessionId) {
        System.out.println(sessionId + " on logon");
    }

    public void onLogout(SessionID sessionId) {
        System.out.println(sessionId.getTargetCompID()+" logout");
    }

    public void toAdmin(Message message, SessionID sessionId) {
        System.out.println(sessionId+" toAdmin");
    }

    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
        System.out.println(sessionId+" toApp");
    }

    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println(sessionId+" fromAdmin");
    }

    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        // insert the id into database
        System.out.println("Receiver fromApp..  " + message);
        String orderId = message.getField(new StringField(17)).getValue();
        Long tmpId = Long.valueOf(message.getField(new IntField(18)).getValue());
//        orderService.updateOrderId(tmpId, orderId);
        OrderService orderService = SpringContextUtil.getBean("OrderService");
        orderService.updateOrderId(tmpId, orderId);
    }

    @Override
    public void onMessage(NewOrderSingle order, SessionID sessionID) {
        System.out.println("Receiver onMessage..  " + order);
    }
}
