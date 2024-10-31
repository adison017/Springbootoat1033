package com.adison.crud1033.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // เส้นทางที่ต้องการอนุญาต
                .allowedOrigins("http://localhost:3000") // URL ของแอป React
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // เมธอดที่อนุญาต
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
