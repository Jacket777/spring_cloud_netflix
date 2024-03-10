package com.msb.order.feignConfiguration;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;

public class StockFeignConfiguration {
    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options(){
        return new Request.Options(2000,5000);
    }
}
