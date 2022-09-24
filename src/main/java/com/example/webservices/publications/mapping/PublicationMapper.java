package com.example.webservices.publications.mapping;

import com.example.webservices.publications.domain.model.entity.Publication;
import com.example.webservices.publications.resource.PublicationResource;
import com.example.webservices.publications.resource.create.CreatePublication;
import com.example.webservices.publications.resource.update.UpdatePublication;
import com.example.webservices.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class PublicationMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public PublicationResource toResource(Publication model) {
        return mapper.map(model, PublicationResource.class);
    }

    public List<PublicationResource> modelListToPage(List<Publication> modelList){

        return mapper.mapList(modelList, PublicationResource.class);
    }

    public Publication toModel(CreatePublication resource) {
        return mapper.map(resource, Publication.class);
    }

    public Publication toModel(UpdatePublication resource) {
        return mapper.map(resource, Publication.class);
    }
}