package com.msb.zuul.config;


import com.msb.zuul.filter.AccessLogFilter;
import com.msb.zuul.filter.BlackIPFilter;
import com.msb.zuul.filter.MyFilter;
import com.msb.zuul.filter.PreRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {
    @Bean
    public MyFilter initMyFilter(){
        return new MyFilter();
    }

    @Bean
    public BlackIPFilter initBlackIPFilter(){
        return new BlackIPFilter();
    }


    @Bean
    public PreRequestFilter initPreRequestFilter(){
        return new PreRequestFilter();
    }

    @Bean
    public AccessLogFilter initAccessLogFilter(){
        return new AccessLogFilter();
    }
}
