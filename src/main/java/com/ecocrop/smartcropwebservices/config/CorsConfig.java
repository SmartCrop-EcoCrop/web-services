package com.ecocrop.smartcropwebservices.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**") // Aplica a todos tus endpoints
                .allowedOrigins("http://localhost:4200", "https://tu-dominio-angular.com") // Añade tu dominio local y de despliegue
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite los métodos que usas
                .allowedHeaders("*"); // Permite todos los encabezados
    }
}