package com.example.webservices.details.mapping;

import com.example.webservices.details.domain.model.entity.Detail;
import com.example.webservices.details.resource.DetailResource;
import com.example.webservices.details.resource.create.CreateDetail;
import com.example.webservices.details.resource.update.UpdateDetail;
import com.example.webservices.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DetailMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public DetailResource toResource(Detail model) {
        return mapper.map(model, DetailResource.class);
    }

    public Detail toModel(CreateDetail resource) {
        return mapper.map(resource, Detail.class);
    }

    public Detail toModel(UpdateDetail resource) {
        return mapper.map(resource, Detail.class);
    }

    public Page<DetailResource> modelListToPage(List<Detail> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, DetailResource.class), pageable, modelList.size());
    }
}
