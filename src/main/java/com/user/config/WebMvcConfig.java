package com.user.config;

import com.user.session.LoginCheckInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@ComponentScan(basePackages = "com.user.config")
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginCheckInterceptor())
                //게시글 List는 모두 볼 수 있으나 R/W 권한은 로그인한 상태에서 가능
                .addPathPatterns("/dagnContents/**")
                .addPathPatterns("/dagnDelete/**")
                .addPathPatterns("/dagnInsert")
                .addPathPatterns("/dagnRegister")
                .excludePathPatterns("/user/login"); // 로그인 페이지는 예외로 처리
    }
}
