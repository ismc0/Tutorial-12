package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration

public class CorsConfig implements WebMvcConfigurer {


@Override

public void addCorsMappings(CorsRegistry registry) {

registry.addMapping("/*") // Aplica a todos los endpoints

.allowedOrigins("*") // Dominios permitidos

.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos

.allowedHeaders("*") // Permite todos los encabezados

.allowCredentials(true); // Permite el envío de cookies y credenciales

}

}
