package com.example.webservices.shared.mapping;


import com.example.webservices.users.mapping.UserOwnerMapper;
import com.example.webservices.users.mapping.UserTenantMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("boncesModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }

    @Bean
    public UserOwnerMapper userOwnerMapper() { return new UserOwnerMapper(); }

    @Bean
    public UserTenantMapper userTenantMapper() { return new UserTenantMapper(); }
    //

}
