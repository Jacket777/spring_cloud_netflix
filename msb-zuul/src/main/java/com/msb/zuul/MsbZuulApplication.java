package com.msb.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class MsbZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsbZuulApplication.class);
    }
}
