package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestServiceCorsApplications {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceCorsApplications.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/senso").allowedOrigins("http://ec2-18-118-163-255.us-east-2.compute.amazonaws.com:8080");
                registry.addMapping("/database").allowedOrigins("http://ec2-18-118-163-255.us-east-2.compute.amazonaws.com:8080");
            }
        };
    }

}