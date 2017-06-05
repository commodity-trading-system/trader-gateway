package com.cts.service;

import com.cts.dao.FutureDao;
import com.cts.entity.Future;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fy on 2017/6/4.
 */
@Service
public class FutureServiceImpl implements FutureService{
    @Resource
    private FutureDao futureDao;

    public List<Future> getFuturesByName(String name) {
        return futureDao.getFuturesByName(name);
    }

    public String getFutureById(Long id) {
        return futureDao.getFutureById(id);
    }
}
