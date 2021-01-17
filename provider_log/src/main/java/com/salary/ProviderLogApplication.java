package com.salary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.salary.mapper")
public class ProviderLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderLogApplication.class, args);
    }
}
