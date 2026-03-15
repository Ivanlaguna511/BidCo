package com.bidco.api_rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // Permitimos local y dejamos abierta la puerta a Vercel
                .allowedOriginPatterns("http://localhost:4200", "https://*.vercel.app") 
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Esto sirve para servir las imágenes que subas
        // OJO: En Render los archivos se borran al reiniciar si no usas un "Disk"
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}