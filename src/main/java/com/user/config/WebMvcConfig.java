package com.user.config;

import com.user.session.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new LoggerInterceptor()).excludePathPatterns("/dagn/**", "/user/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/**/*loginclick*")
                .excludePathPatterns("/*log*");
    }
}
