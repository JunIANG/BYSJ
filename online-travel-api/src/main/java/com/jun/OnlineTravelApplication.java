package com.jun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 */
@MapperScan("com.jun.mapper")
@SpringBootApplication
public class OnlineTravelApplication {
    public static void main(String[] args) {

        SpringApplication.run(OnlineTravelApplication.class, args);

    }
}
