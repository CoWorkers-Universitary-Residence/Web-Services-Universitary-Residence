package com.example.webservices.subscriptions.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("subscriptionsMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public SubscriptionMapper subscriptionMapper(){
        return new SubscriptionMapper();
    }
}
