package com.etn.shoppingmall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.etn.shoppingmall.common", "com.etn.shoppingmall.core","com.etn.shoppingmall.admin"})
@MapperScan("com.etn.shoppingmall.core.mapper")
@EnableTransactionManagement
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
