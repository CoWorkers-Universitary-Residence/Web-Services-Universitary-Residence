package com.example.webservices.Interactions.mapping;

import com.example.webservices.Interactions.domain.entity.Date;
import com.example.webservices.Interactions.resource.DateResource;
import com.example.webservices.Interactions.resource.create.CreateDateResource;
import com.example.webservices.Interactions.resource.update.UpdateDateResource;
import com.example.webservices.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DateMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public DateResource toResource(Date model) { return mapper.map(model, DateResource.class); }

    public Date toModel(CreateDateResource resource) { return mapper.map(resource, Date.class); }

    public Date toModel(UpdateDateResource resource) { return mapper.map(resource, Date.class); }

    public Page<DateResource> modelListPage (List<Date> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, DateResource.class), pageable, modelList.size());
    }
}