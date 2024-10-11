package com.example.transactbridge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class RestApiConfig implements WebMvcConfigurer {

    // Customize the object mapper for JSON serialization
    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return Jackson2ObjectMapperBuilder.json().indentOutput(true);
    }

    // Additional configurations (CORS, etc.) can be added here if needed
}
