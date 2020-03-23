package com.lyy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.lyy"})
public class LearingOnlineAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearingOnlineAdminApplication.class, args);
    }

}
