package com.bidco.api_rest.config; // Asegúrate de que el paquete sea correcto

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todas las rutas
                .allowedOrigins("http://localhost:4200") // Origen de Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Cabeceras permitidas (Authorization, Content-Type, etc.)
                .allowCredentials(true); // Permite cookies o autenticación
    }
}