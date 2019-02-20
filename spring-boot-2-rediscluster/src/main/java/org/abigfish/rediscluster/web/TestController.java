package org.abigfish.rediscluster.web;

import org.abigfish.rediscluster.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    RedisService redisService;

    @GetMapping(value = "/testset")
    public Object testSet(){
    	redisService.set("abigfish","hello abigfish");
        System.out.println(redisService.get("abigfish"));
        return null;
    }

}