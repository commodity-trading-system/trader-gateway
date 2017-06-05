package com.cts.entity;

/**
 * Created by fy on 2017/6/4.
 */
public class Future {
    Long id;
    String name;
    String period;

    public void setId(Long id){this.id=id;}
    public Long getId(){return this.id;}
    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}
    public void setPeriod(String period){this.period=period;}
    public String getPeriod(){return this.period;}
}
