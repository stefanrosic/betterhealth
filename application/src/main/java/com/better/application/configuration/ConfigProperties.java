package com.better.application.configuration;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Configuration
@ConfigurationProperties(prefix = "data")
public class ConfigProperties {

    private String input;

    private String output;

    private String error;
}
