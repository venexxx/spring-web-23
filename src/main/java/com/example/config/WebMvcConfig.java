package com.example.config;

import com.example.interceptor.CustomInterceptor;
import com.example.service.BrandService;
import com.example.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final BrandService brandService;
    private final UserService userService;

    public WebMvcConfig(BrandService brandService, UserService userService) {
        this.brandService = brandService;
        this.userService = userService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor());
    }

    @Bean
    public CustomInterceptor customInterceptor() {
        return new CustomInterceptor(brandService,userService);
    }
}