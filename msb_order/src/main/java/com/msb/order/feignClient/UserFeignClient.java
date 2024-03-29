package com.msb.order.feignClient;


import com.msb.order.feignClient.downgrade.UserFeignClientFallBack;
import com.msb.order.feignClient.downgrade.UserFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "msb-user",fallbackFactory = UserFeignClientFallbackFactory.class)
//@FeignClient(name = "msb-user",fallback = UserFeignClientFallBack.class)
//@FeignClient(name = "msb-user") //不使用feignclient
public interface UserFeignClient {
    @RequestMapping("/getUserInfo")
    String getUser(String userId);
}
