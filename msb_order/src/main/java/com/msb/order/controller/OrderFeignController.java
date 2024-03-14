package com.msb.order.controller;
import com.msb.order.feignClient.StockFeignClient;
import com.msb.order.service.OrderServiceAPI;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderFeignController {
    @Autowired
    private StockFeignClient stockFeignClient;

    @Autowired
    private OrderServiceAPI userService;

    @RequestMapping("createOrder")
    public String createOrder(String productId){
        stockFeignClient.reduce(productId);
        log.debug("下单成功");
        return "OKKKK";

    }

    /**
     * 引入Hystrix
     * @param userId
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "200"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
            })
    @RequestMapping("createOrderByHystrix")
    public String createOrder_hystrix(String userId){
        String userInfo = userService.getUserInfo(userId);
        return "创建订单成功，用户信息 userId: "+userId;

    }


    public String fallbackMethod(String userId) {
        return " 服务降级: 用户id = " + userId;
    }

}
