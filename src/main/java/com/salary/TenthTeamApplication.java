package com.salary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.salary.mapper")
public class TenthTeamApplication {

    public static void main(String[] args) {
        //下面语句使得Log4j2日志输出使用异步处理，减小输出日志对性能的影响
        System.setProperty("Log4jContextSelector",
                "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");

        SpringApplication.run(TenthTeamApplication.class, args);
    }
}
