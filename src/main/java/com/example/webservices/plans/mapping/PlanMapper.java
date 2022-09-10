package com.example.webservices.plans.mapping;

import com.example.webservices.plans.domain.model.entity.Plan;
import com.example.webservices.plans.resource.CreatePlanResource;
import com.example.webservices.plans.resource.PlanResource;
import com.example.webservices.plans.resource.UpdatePlanResource;
import com.example.webservices.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class PlanMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public PlanResource toResource(Plan model) {
        return mapper.map(model, PlanResource.class);
    }

    public List<PlanResource> modelListToPage(List<Plan> modelList){
        return mapper.mapList(modelList, PlanResource.class);
    }

    public Plan toModel(CreatePlanResource resource) {
        return mapper.map(resource, Plan.class);
    }

    public Plan toModel(UpdatePlanResource resource) {
        return mapper.map(resource, Plan.class);
    }
}
