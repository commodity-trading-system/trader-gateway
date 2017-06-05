package com.cts.service;

import com.cts.dao.TraderDao;
import com.cts.entity.Trader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by fy on 2017/6/3.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TraderServiceImpl implements TraderService {
    @Resource
    private TraderDao traderDao;

    public Trader getTraderByName(String name) {
        return traderDao.getTraderByName(name);
    }
}
