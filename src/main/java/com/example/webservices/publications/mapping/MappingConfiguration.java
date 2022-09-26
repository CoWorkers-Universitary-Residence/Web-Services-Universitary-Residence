package com.example.webservices.publications.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("PublicationMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PublicationMapper publicationMapper(){ return new PublicationMapper(); }
}
