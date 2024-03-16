package com.msb.zuul.config;


import com.msb.zuul.filter.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {
    @Bean
    public MyFilter initMyFilter(){
        return new MyFilter();
    }
}
