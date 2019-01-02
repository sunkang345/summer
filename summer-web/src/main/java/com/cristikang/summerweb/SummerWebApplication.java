package com.cristikang.summerweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.cristikang"})
public class SummerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummerWebApplication.class, args);
    }

}

