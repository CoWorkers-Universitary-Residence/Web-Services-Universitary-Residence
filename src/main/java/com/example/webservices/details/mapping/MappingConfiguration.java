package com.example.webservices.details.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("DetailMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public DetailMapper detailMapper(){ return new DetailMapper(); }
}
