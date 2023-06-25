package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//exclude 추가
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) //security 기본 메인화면 예외처리
public class WebBikesApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBikesApplication.class, args);
    }

}
