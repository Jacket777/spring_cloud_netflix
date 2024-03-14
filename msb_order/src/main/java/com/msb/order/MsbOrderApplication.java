package com.msb.order;

//import com.msb.cluster.ribbonConfiguration.StockConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@EnableDiscoveryClient // 这是官方提供的  ,我们以后可能切换其他的注册中心比如说nacos，那我们就直接切换就行了
//@EnableEurekaClient  // 是netflix提供的，如果用这个注解就只能服务于eureka
@SpringBootApplication
@EnableFeignClients
public class MsbOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsbOrderApplication.class);
    }
}
