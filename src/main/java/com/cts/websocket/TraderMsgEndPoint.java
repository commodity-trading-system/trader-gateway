package com.cts.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cts.entity.Message;
import com.cts.util.HttpUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by fy on 2017/6/4.
 */
@ServerEndpoint("/future")
public class TraderMsgEndPoint {
    /* Queue for all open WebSocket sessions */
    public static final String orderStatus[] = {"Canceled", "Appending", "Partial", "Finished", "Invalid"};

    static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();

    static Map<Session, Long> subscribes = new ConcurrentHashMap<Session, Long>();

    private boolean sendMarket(Session session, JSONObject node){
        String info[] = node.getString("value").split(",");
        String buyInfo[] = info[0].split("=");
        String sellInfo[] = info[1].split("=");
        JSONObject response = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("BuyQty", buyInfo[0]);
        data.put("BuyPrice", buyInfo[1]);
        data.put("SellQty", sellInfo[0]);
        data.put("SellPrice", sellInfo[1]);
        response.put("type", 3);
        response.put("data", data);
        if(!queue.contains(session)){
            System.out.println(session.getId()+" connection has closed");
            return false;
        }
        try {
            session.getBasicRemote().sendText(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void getFloatingMarket(Session session, String futureId){
        String latest = HttpUtil.sendGet("http://139.224.236.65:2379/v2/keys/futures/"+futureId+"/latest",null);
        JSONObject obj  = JSONObject.parseObject(latest);
        JSONObject node = obj.getJSONObject("node");
        Integer waitIndex = node.getInteger("modifiedIndex");
        while(true){
            waitIndex++;
            latest = HttpUtil.sendGet("http://139.224.236.65:2379/v2/keys/futures/"+futureId+"/latest", "wait=true&waitIndex="+waitIndex.toString());
            obj = JSONObject.parseObject(latest);
            node = obj.getJSONObject("node");
            if(!sendMarket(session, node))
                break;
        }
    }

//    private void updateOrderStatus(Session session, String orderId) {
//        String latest = HttpUtil.sendGet("http://139.224.236.65:2379/v2/keys/consignations/"+orderId+"/status", null);
//        JSONObject obj  = JSONObject.parseObject(latest);
//        JSONObject node = obj.getJSONObject("node");
//        Integer waitIndex = node.getInteger("modifiedIndex");
//        while(true){
//            waitIndex++;
//            latest = HttpUtil.sendGet("http://139.224.236.65:2379/v2/keys/consignations/"+orderId+"/status", "wait=true&waitIndex="+waitIndex.toString());
//            if(!queue.contains(session)){
//                System.out.println(session.getId()+" connection has closed");
//                break;
//            }
//            obj = JSONObject.parseObject(latest);
//            node = obj.getJSONObject("node");
//            Integer index =Integer.valueOf(node.getString("value"));
//            String status = orderStatus[index];
//            JSONObject  response= new JSONObject();
//            response.put("type", 3);
//            response.put("data", status);
//            try {
//                session.getBasicRemote().sendText(response.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private void getFloatingPrice(Session session, String futureId){
        String latest = HttpUtil.sendGet("http://139.224.236.65:2379/v2/keys/futures/"+futureId+"/latest",null);
        JSONObject obj  = JSONObject.parseObject(latest);
        JSONObject node = obj.getJSONObject("node");
        Integer waitIndex = node.getInteger("modifiedIndex");
        while(true){
            waitIndex++;
            latest = HttpUtil.sendGet("http://139.224.236.65:2379/v2/keys/futures/"+futureId+"/latest", "wait=true&waitIndex="+waitIndex.toString());
            if(!queue.contains(session)){
                System.out.println(session.getId()+" connection has closed");
                break;
            }
            if("".equals(latest))continue;
            obj = JSONObject.parseObject(latest);
            node = obj.getJSONObject("node");
            String info[] = node.getString("value").split(",");
            JSONObject response = new JSONObject();
            JSONObject data = new JSONObject();
            data.put("FutureId", futureId);
            data.put("Price", info[0]);
            data.put("CreatedAt", info[1]);
            response.put("type", 2);
            response.put("data", data);
            // send the latest price & time
            try {
                session.getBasicRemote().sendText(response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnMessage
    public void onMessage(final Session session, String message){
        System.out.println(message);
        JSONObject msgJson = JSONObject.parseObject(message);
        if(msgJson.getInteger("type").equals(1)){
            Long futureId = Long.valueOf(msgJson.getString("data"));
            String url = "http://139.224.236.65:5002/futures/"+futureId;
            String history = HttpUtil.sendGet(url, "limit=20");
            JSONArray array = JSONArray.parseArray(history);
            JSONObject response = new JSONObject();
            response.put("type", 1);
            response.put("data", array);
            try {
                session.getBasicRemote().sendText(response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            subscribes.put(session, futureId);
            getFloatingPrice(session, futureId.toString());
        } else if(msgJson.getInteger("type").equals(2)){
            String orderId = msgJson.getString("data");
            String url = "http://139.224.236.65:2379/v2/keys/consignations/"+orderId+"/status";
            String stat = HttpUtil.sendGet(url, null);
            JSONObject result = JSONObject.parseObject(stat);
            JSONObject node = result.getJSONObject("node");
            Integer index =Integer.valueOf(node.getString("value"));
            String status = orderStatus[index];
            JSONObject  response= new JSONObject();
            response.put("type", 3);
            response.put("data", status);
            try {
                session.getBasicRemote().sendText(response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
//            WebSocketComponent.listenOnOrderStatus(session, orderId);
            WebSocketComponent.yo();
            System.out.println("return !");
        } else if(msgJson.getInteger("type").equals(3)){
            System.out.println("tracing market...");
        }

    }

    @OnOpen
    public void openConnection(Session session) {
        /* Register this connection in the queue */
        queue.add(session);
        System.out.println("connection opened");
    }

    @OnClose
    public void closedConnection(Session session) {
        /* Remove this connection from the queue */
        queue.remove(session);
        System.out.println("connection closed");
    }

    @OnError
    public void error(Session session, Throwable t) {
        /* Remove this connection from the queue */
        queue.remove(session);
        System.out.println(t.toString());
        System.out.println("error");
    }
}
