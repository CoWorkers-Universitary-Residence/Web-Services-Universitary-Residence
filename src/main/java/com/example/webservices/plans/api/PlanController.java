package com.example.webservices.plans.api;

import com.example.webservices.plans.domain.services.PlanService;
import com.example.webservices.plans.mapping.PlanMapper;
import com.example.webservices.plans.resource.CreatePlanResource;
import com.example.webservices.plans.resource.PlanResource;
import com.example.webservices.plans.resource.UpdatePlanResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
public class PlanController {
    @Autowired
    private PlanService planService;

    @Autowired
    private PlanMapper mapper;

    @GetMapping
    public List<PlanResource> getAllPlans(){
        return mapper.modelListToPage(planService.getAll());
    }

    @GetMapping("{planId}")
    public PlanResource getPlanById(@PathVariable("planId") Long planId) {
        return mapper.toResource(planService.getById(planId));
    }

    @PostMapping
    public PlanResource createPlan(@RequestBody CreatePlanResource request){
        return mapper.toResource(planService.create(mapper.toModel(request)));
    }

    @PutMapping("{planId}")
    public PlanResource updatePlan(@PathVariable Long planId, @RequestBody UpdatePlanResource request){
        return mapper.toResource(planService.update(planId, mapper.toModel(request)));
    }

    @DeleteMapping("{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable Long planId){
        return planService.delete(planId);
    }
}
