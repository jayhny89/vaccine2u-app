package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppConfiguration {

    @Autowired
    private AppProperties appProperties;

    @Bean
    public ModelMapper converterModelMapper(){return new ModelMapper();}

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.setConnectTimeout(Duration.ofMillis(appProperties.getTimeOutConnect()))
                .setReadTimeout(Duration.ofMillis(appProperties.getTimeOutRead()))
                .build();
    }
}
