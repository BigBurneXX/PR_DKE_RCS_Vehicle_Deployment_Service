package com.example.backend.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC configuration class for CORS settings.
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * Configures CORS mappings.
     *
     * @param registry CorsRegistry to configure CORS settings.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Allow all origins
                .allowedMethods("GET", "POST", "DELETE", "OPTION") // Allow specific HTTP methods
                .allowedHeaders("*"); // Allow all headers
    }
}
