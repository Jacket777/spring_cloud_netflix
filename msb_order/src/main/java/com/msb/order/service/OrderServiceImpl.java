package com.msb.order.service;

import com.msb.order.feignClient.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderServiceAPI {
    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public String getUserInfo(String userId)  {
//模拟触发异常，配置超时200毫秒，此时休眠1秒，则触发异常
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String result = userFeignClient.getUser(userId);
        return result;

    }
}
