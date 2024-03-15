package com.msb.order.feignClient.downgrade;

import com.msb.order.feignClient.UserFeignClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 根据不同异常进行降级
 */
@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public String getUser(String userId) {
                String message = throwable.getMessage();

                return "不同异常不同的返回";
            }
        };

    }
}
