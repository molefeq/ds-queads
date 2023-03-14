package com.que.ads.ds.service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.que.ads.ds.security"
})
@EnableJpaRepositories(basePackages = {"com.que.ads.ds.security.repository"})
@EntityScan(basePackages = {
        "com.que.ads.ds.security.entity"})
public class QueAdsSecurityConfig {
}
