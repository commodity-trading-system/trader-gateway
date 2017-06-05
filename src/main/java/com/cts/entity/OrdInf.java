package com.cts.entity;

/**
 * Created by fy on 2017/6/4.
 */
public class OrdInf {
    String orderId;
    Long traderId;
    Long id;
    String brokerName;

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public void setId(Long id){this.id=id;}
    public Long getId(){return this.id;}
    public void setOrderId(String orderId){this.orderId=orderId;}
    public String getOrderId(){return this.orderId;}
    public void setTraderId(Long traderId){this.traderId=traderId;}
    public Long getTraderId(){return this.traderId;}

}
