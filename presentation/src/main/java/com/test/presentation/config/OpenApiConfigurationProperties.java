package com.test.presentation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "openapi")
public class OpenApiConfigurationProperties {
    private String title;
    private String description;
    private String version;
    private String url;
}
