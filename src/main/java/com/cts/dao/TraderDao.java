package com.cts.dao;

import com.cts.entity.Trader;

/**
 * Created by fy on 2017/6/3.
 */
public interface TraderDao {
    Trader getTraderByName(String name);
}
