package com;
/**
 * 用户服务提供方
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.mapper")
public class FeedbackServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeedbackServiceApplication.class,args);
    }
}
