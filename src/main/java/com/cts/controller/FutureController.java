package com.cts.controller;

import com.cts.entity.Future;
import com.cts.service.FutureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fy on 2017/6/2.
 */
@RestController
public class FutureController {
    @Resource
    private FutureService futureService;

    @RequestMapping(value = "/future/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Future>> listAllUsers(@PathVariable("name") String name) {
        List<Future> futures = futureService.getFuturesByName(name);
        if(futures.isEmpty()){
            return new ResponseEntity<List<Future>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Future>>(futures, HttpStatus.OK);
    }

}
