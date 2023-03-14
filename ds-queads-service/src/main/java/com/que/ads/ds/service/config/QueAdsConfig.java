package com.que.ads.ds.service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.que.ads.ds.common.advices"
})
@EnableJpaRepositories(basePackages = {"com.que.ads.ds.service.repository"})
@EntityScan(basePackages = {
        "com.que.ads.ds.data.entity"})
public class QueAdsConfig {
}
