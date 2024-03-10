package com.msb.order.controller;
import com.msb.order.feignClient.StockFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderFeignController {
    @Autowired
    private StockFeignClient stockFeignClient;

    @RequestMapping("createOrder")
    public String createOrder(String productId){
        stockFeignClient.reduce(productId);
        log.debug("下单成功");
        return "OKKKK";

    }
}
