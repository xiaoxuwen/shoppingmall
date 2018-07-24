package com.etn.shoppingmall.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.etn.shoppingmall.wx","com.etn.shoppingmall.common", "com.etn.shoppingmall.core"})
@MapperScan("com.etn.shoppingmall.core.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}