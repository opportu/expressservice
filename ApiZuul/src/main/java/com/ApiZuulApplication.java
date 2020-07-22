package com;

import com.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * API网关
 */
@SpringCloudApplication
@EnableZuulProxy
public class ApiZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiZuulApplication.class,args);
    }

    @Bean
    public AccessFilter accessFilter () {
        return new AccessFilter();
    }
}
