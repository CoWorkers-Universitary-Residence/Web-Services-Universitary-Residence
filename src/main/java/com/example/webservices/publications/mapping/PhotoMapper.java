package com.example.webservices.publications.mapping;

import com.example.webservices.publications.domain.model.entity.Photo;
import com.example.webservices.publications.resource.PhotoResource;
import com.example.webservices.publications.resource.create.CreatePhoto;
import com.example.webservices.publications.resource.update.UpdatePhoto;
import com.example.webservices.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class   PhotoMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public PhotoResource toResource(Photo model) {
        return mapper.map(model, PhotoResource.class);
    }

    public List<PhotoResource> modelListToPage(List<Photo> modelList){

        return mapper.mapList(modelList, PhotoResource.class);
    }

    public Photo toModel(CreatePhoto resource) {
        return mapper.map(resource, Photo.class);
    }

    public Photo toModel(UpdatePhoto resource) {
        return mapper.map(resource, Photo.class);
    }
}
