package com.example.webservices.plans.domain.services;

import com.example.webservices.plans.domain.model.entity.Plan;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanService {
    List<Plan> getAll();
    Plan getById(Long planId);
    Plan create(Plan request);
    Plan update(Long planId, Plan request);
    ResponseEntity<?> delete(Long planId);
}
