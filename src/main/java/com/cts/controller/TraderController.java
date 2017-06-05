package com.cts.controller;

import com.alibaba.fastjson.JSONObject;
import com.cts.entity.Order;
import com.cts.entity.Order.OrdType;
import com.cts.entity.Order.Side;
import com.cts.entity.Trader;
import com.cts.fix.FixInitiator;
import com.cts.service.OrderService;
import com.cts.service.TraderService;
import com.cts.util.HttpUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fy on 2017/6/2.
 */
@Controller
@RequestMapping("/user")
public class TraderController {
    @Resource
    private OrderService orderService;

    @Resource
    private FixInitiator initiator;

    @Resource
    private TraderService traderService;


    @ResponseBody
    @RequestMapping("/futureStatus")
    public JSONObject getStatusTest() {
        String result = HttpUtil.sendGet("http://139.224.236.65:2379/v2/keys/futures/1/buy", null);
        JSONObject response = JSONObject.parseObject(result);
        return  response;
    }

    @RequestMapping("/login")
    public String login(String name, String password, HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            subject.login(token);
            session.putValue("username", name);
            return "index";
        } catch (UnknownAccountException uae) {
            uae.printStackTrace();
        } catch (IncorrectCredentialsException ice) {
            ice.printStackTrace();
        } catch (LockedAccountException lae){
            lae.printStackTrace();
        }
        return "redirect:http://localhost:8080/trader/";
    }

    @RequestMapping("/send")
    public String sendMsg(){
        Order order = new Order();
        order.setType(OrdType.MARKET);
        order.setFuture(1l);
        order.setPrice(new Float(4.5));
        order.setSide(Side.BUY);
        order.setQty(12);
        order.setBrokerName("BrokerGateway1");
        orderService.sendOrder(order, 0l);
        return "redirect:http://localhost:8080/trader/";
    }

}
