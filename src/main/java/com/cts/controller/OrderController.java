package com.cts.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cts.entity.Order;
import com.cts.fix.FixInitiator;
import com.cts.service.FutureService;
import com.cts.service.OrderService;
import com.cts.util.HttpUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.asm.Type;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by fy on 2017/6/2.
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private FutureService futureService;

    @Resource
    private FixInitiator initiator;

    @RequestMapping(value = "/order/", method = RequestMethod.POST)
    public ResponseEntity<Void> newOrder(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
        // send the order to the broker and get result id
        HttpHeaders headers = new HttpHeaders();
        Long orderId = orderService.insertOrder(1l, order.getBrokerName());
        if(order.getQty() > 1000000){
            orderService.iceBurg(order);
        }else{
            orderService.sendOrder(order, orderId);
            while(orderService.getOrderId(orderId).equals("unknown")){
                continue;
            }
        }
        headers.setLocation(ucBuilder.path("/order/{id}").buildAndExpand(orderService.getOrderId(orderId)).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
//    @RequiresRoles(value = {"trader"})
    public ResponseEntity<Order> cancelOrder(@PathVariable("id") String id) {
        System.out.println("Cancel Order with id " + id);
        Order order =new Order();
        order.setBrokerName(orderService.getBrokerName(id));
        order.setSide(Order.Side.BUY);
        order.setPrice(0f);order.setQty(0);order.setFuture(0l);
        order.setId(id);order.setType(Order.OrdType.CANCEL);
        orderService.sendOrder(order, 1l);
        // send the cancel request to the broker gateway
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequiresAuthentication
//    @RequiresRoles(value = {"trader"})
    public ResponseEntity<Order> getOrder(@PathVariable("id") String id) {
        System.out.println("Fetching Order with id " + id);
        // retrieve the order from the broker gateway
        Order order = new Order();
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/", method = RequestMethod.GET)
    public ResponseEntity<JSONArray> listAllOrders() {
        List<Order> orders = new ArrayList<Order>();
        String tmp = HttpUtil.sendGet("http://139.224.236.65:5002/firms/1/consignations","limit=20&offset=0");
        if("".equals(tmp)){
            return new ResponseEntity<JSONArray>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        JSONArray array = JSONArray.parseArray(tmp);
        for(Object obj:array){
            JSONObject data = (JSONObject)obj;
            data.put("FutureId", futureService.getFutureById(data.getLong("FutureId")));
        }
        return new ResponseEntity<JSONArray>(array, HttpStatus.OK);
    }
}
