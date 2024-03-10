package com.msb.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class UserController {

    @Value("${server.port}")
    private int port;
    
    @RequestMapping("/getUserInfo")
    public String getUser(String userId){
        log.info("获取用户信息 userId:{},port:{}",userId,port);
        return "善缘";
    }
}
