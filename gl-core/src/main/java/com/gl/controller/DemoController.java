package com.gl.controller;

import com.gl.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private RedisService redisService;

    @GetMapping("hello")
    public String sayHello(){
        System.out.println(redisService);
        return "hello";
    }
}
