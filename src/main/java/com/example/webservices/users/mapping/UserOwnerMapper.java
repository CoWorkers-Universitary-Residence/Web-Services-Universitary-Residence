package com.example.webservices.users.mapping;

import com.example.webservices.shared.mapping.EnhancedModelMapper;
import com.example.webservices.users.domain.model.entity.UserOwner;
import com.example.webservices.users.resource.create.CreateUserOwnerResource;
import com.example.webservices.users.resource.update.UpdateUserOwnerResource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserOwnerMapper {
    @Autowired
    private EnhancedModelMapper mapper;

    public UpdateUserOwnerResource toResource(UserOwner model) {
        return mapper.map(model, UpdateUserOwnerResource.class);
    }

    public UserOwner toModel(CreateUserOwnerResource resource) {
        return mapper.map(resource, UserOwner.class);
    }

    public UserOwner toModel(UpdateUserOwnerResource resource) {
        return mapper.map(resource, UserOwner.class);
    }
}
