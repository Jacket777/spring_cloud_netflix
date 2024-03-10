package com.msb.order.feignClient;

import com.msb.order.feignConfiguration.StockFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msb-stock", configuration = StockFeignConfiguration.class)
public interface StockFeignClient {
    @GetMapping("/stock/reduce/{produceId}")
    String reduce(@PathVariable("produceId") String produceId);
}
