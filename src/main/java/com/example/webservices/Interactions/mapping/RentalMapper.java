package com.example.webservices.Interactions.mapping;

import com.example.webservices.Interactions.domain.entity.Rental;
import com.example.webservices.Interactions.resource.RentalResource;
import com.example.webservices.Interactions.resource.create.CreateRentalResource;
import com.example.webservices.Interactions.resource.update.UpdateRentalResource;
import com.example.webservices.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class RentalMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public RentalResource toResource(Rental model) { return mapper.map(model, RentalResource.class); }

    public Rental toModel(CreateRentalResource resource) { return mapper.map(resource, Rental.class); }

    public Rental toModel(UpdateRentalResource resource) { return mapper.map(resource, Rental.class); }

    public Page<RentalResource> modelListPage (List<Rental> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, RentalResource.class), pageable, modelList.size());
    }
}