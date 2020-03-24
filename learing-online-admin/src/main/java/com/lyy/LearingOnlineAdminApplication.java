package com.lyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(basePackages = {"com.lyy.dao"})
@SpringBootApplication(scanBasePackages = {"com.lyy"})
public class LearingOnlineAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearingOnlineAdminApplication.class, args);
    }

}
