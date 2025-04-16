package com.example.mvc_demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtConfig {
    private String jwtCookieName;
    private String jwtRefreshCookieName;
    private String jwtSecret;
    private long jwtAccessExpirationMs;
    private long jwtRefreshExpirationMs;
}
