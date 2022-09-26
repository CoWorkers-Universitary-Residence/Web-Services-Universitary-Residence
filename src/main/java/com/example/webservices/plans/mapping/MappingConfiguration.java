package com.example.webservices.plans.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("plansMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PlanMapper planMapper() {
        return new PlanMapper();
    }
}
