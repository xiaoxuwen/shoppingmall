package com.etn.shoppingmall.os;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.etn.shoppingmall.common", "com.etn.shoppingmall.core", "com.etn.shoppingmall.os"})
@MapperScan("com.etn.shoppingmall.core.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}