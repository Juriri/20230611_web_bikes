package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

//exclude 추가
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) //security 기본 메인화면 예외처리
@ComponentScan(basePackages = {"com", "com.user.config"})
public class WebBikesApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBikesApplication.class, args);
    }

}
