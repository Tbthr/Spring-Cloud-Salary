package com.salary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProviderBillApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderBillApplication.class, args);
    }
}
