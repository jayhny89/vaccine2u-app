package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.app.config")
@Data
public class AppProperties {
    private String hostName;
    private String locationURL;
    private String registerURL;
    private Long timeOutConnect;
    private Long timeOutRead;
}
