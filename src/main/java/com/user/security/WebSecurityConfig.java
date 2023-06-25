package com.user.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()// 어떤 요청이든 '인증' -> 스프링 서버로 요청이 오는 모든 request 에 대해서 인증을 거치겠다.
                .and().formLogin()
                .defaultSuccessUrl("/")  //로그인 성공시의 URL을 설정.
                .permitAll() // 로그인 기능에 대해서 허가.

                .and()

                // 로그아웃 기능 허용
                .logout()
                .permitAll();

    }
}
