package com.cts.service;

import com.cts.entity.Future;

import java.util.List;

/**
 * Created by fy on 2017/6/4.
 */
public interface FutureService {
    List<Future> getFuturesByName(String name);
    String getFutureById(Long id);
}
