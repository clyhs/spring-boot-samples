package org.abigfish.jediscluster.web;

import org.abigfish.jediscluster.service.RedisClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    RedisClientTemplate redisClientTemplate;

    @GetMapping(value = "/testset")
    public Object testSet(){
        redisClientTemplate.set("abigfish","hello abigfish");
        System.out.println(redisClientTemplate.get("abigfish"));
        return null;
    }

}