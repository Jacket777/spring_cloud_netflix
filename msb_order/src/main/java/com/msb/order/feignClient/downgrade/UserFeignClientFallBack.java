package com.msb.order.feignClient.downgrade;

import com.msb.order.feignClient.UserFeignClient;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallBack implements UserFeignClient {
    @Override
    public String getUser(String userId) {
        return "获取用户失败进行降级：" + userId;
    }
}
