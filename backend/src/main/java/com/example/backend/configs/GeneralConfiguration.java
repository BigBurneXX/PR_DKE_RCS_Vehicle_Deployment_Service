package com.example.backend.configs;


import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * General configuration class for bean definitions.
 */
@Configuration
public class GeneralConfiguration {
    /**
     * Creates a RestTemplate bean.
     *
     * @param builder RestTemplateBuilder to configure the RestTemplate.
     * @return a RestTemplate instance.
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    /**
     * Creates a ModelMapper bean.
     *
     * @return a ModelMapper instance.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
