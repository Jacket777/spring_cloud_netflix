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

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = userFeignClient.getUser(userId);
        return result;

    }
}
