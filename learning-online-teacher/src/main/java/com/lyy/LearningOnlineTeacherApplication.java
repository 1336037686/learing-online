package com.lyy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * YY IS P
 */
@EnableTransactionManagement
@SpringBootApplication
public class LearningOnlineTeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningOnlineTeacherApplication.class, args);
    }

}
