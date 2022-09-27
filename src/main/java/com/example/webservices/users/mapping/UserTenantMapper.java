package com.example.webservices.users.mapping;

import com.example.webservices.shared.mapping.EnhancedModelMapper;
import com.example.webservices.users.domain.model.entity.UserTenant;
import com.example.webservices.users.resource.create.CreateUserTenantResource;
import com.example.webservices.users.resource.update.UpdateUserTenantResource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTenantMapper {
    @Autowired
    private EnhancedModelMapper mapper;

    public UpdateUserTenantResource toResource(UserTenant model) {
        return mapper.map(model, UpdateUserTenantResource.class);
    }

    public UserTenant toModel(CreateUserTenantResource resource) {
        return mapper.map(resource, UserTenant.class);
    }

    public UserTenant toModel(UpdateUserTenantResource resource) {
        return mapper.map(resource, UserTenant.class);
    }
}