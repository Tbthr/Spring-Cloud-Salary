package com.salary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.salary.mapper")
public class ProviderRoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderRoleApplication.class, args);
    }
}
