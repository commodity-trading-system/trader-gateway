package com.cts.websocket;

import com.alibaba.fastjson.JSONObject;
import com.cts.util.HttpUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;

/**
 * Created by fy on 2017/6/5.
 */
@Component
public class WebSocketComponent  {
    private static Integer flag = null;

    @Async
    public static void yo(){
        try {
            Thread.sleep(5000);
            System.out.println("finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public static void listenOnOrderStatus(Session session, String orderId) {
        String latest = HttpUtil.sendGet("http://139.224.236.65:2379/v2/keys/consignations/"+orderId+"/status", null);
        JSONObject obj  = JSONObject.parseObject(latest);
        JSONObject node = obj.getJSONObject("node");
        Integer waitIndex = node.getInteger("modifiedIndex");
        for(int i = 0; i< 5; ++i){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hit:"+i);
        }
        while(true){
            waitIndex++;
            latest = HttpUtil.sendGet("http://139.224.236.65:2379/v2/keys/consignations/"+orderId+"/status", "wait=true&waitIndex="+waitIndex.toString());
            if(!TraderMsgEndPoint.queue.contains(session)){
                System.out.println(session.getId()+" connection has closed");
                break;
            }
            obj = JSONObject.parseObject(latest);
            node = obj.getJSONObject("node");
            Integer index =Integer.valueOf(node.getString("value"));
            String status = TraderMsgEndPoint.orderStatus[index];
            JSONObject  response= new JSONObject();
            response.put("type", 3);
            response.put("data", status);
            try {
                session.getBasicRemote().sendText(response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
