package com.cts.entity;

/**
 * Created by fy on 2017/5/30.
 */
public class Order {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public interface OrdType {
        Integer MARKET = 1;
        Integer LIMIT = 2;
        Integer STOP = 3;
        Integer CANCEL = 4;
    }

    public interface Side {
        Integer BUY = 1;
        Integer SELL  = 2;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSide() {
        return side;
    }

    public void setSide(Integer side) {
        this.side = side;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    private String id;
    private Long future;
    private Integer type;
    private Integer side;
    private Float price;
    private Integer qty;
    private String brokerName;


    public Long getFuture() {
        return future;
    }

    public void setFuture(Long future) {
        this.future = future;
    }
}
