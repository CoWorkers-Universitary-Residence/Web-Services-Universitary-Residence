package com.example.webservices.plans.service;

import com.example.webservices.plans.domain.model.entity.Plan;
import com.example.webservices.plans.domain.persistence.PlanRepository;
import com.example.webservices.plans.domain.services.PlanService;
import com.example.webservices.shared.exception.ResourceNotFoundException;
import com.example.webservices.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PlanServiceImpl implements PlanService {
    private static final String ENTITY = "Plan";
    private final PlanRepository planRepository;
    private final Validator validator;

    public PlanServiceImpl(PlanRepository planRepository, Validator validator) {
        this.planRepository = planRepository;
        this.validator = validator;
    }


    @Override
    public List<Plan> getAll() {
        return planRepository.findAll();
    }

    @Override
    public Plan getById(Long planId) {
        return planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, planId));
    }

    @Override
    public Plan create(Plan request) {
        Set<ConstraintViolation<Plan>> violations = validator.validate(request);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY, violations);
        }
        return planRepository.save(request);
    }

    @Override
    public Plan update(Long planId, Plan request) {
        Set<ConstraintViolation<Plan>> violations = validator.validate(request);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY, violations);
        }
        return planRepository.findById(planId).map(plan ->
                planRepository.save(
                        plan.withType(request.getType())
                                .withPrice(request.getPrice())
                )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, planId));
    }

    @Override
    public ResponseEntity<?> delete(Long planId) {
        return planRepository.findById(planId).map(plan -> {
                    planRepository.delete(plan);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, planId));
    }
}
