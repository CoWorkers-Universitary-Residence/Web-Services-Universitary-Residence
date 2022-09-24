package com.example.webservices.subscriptions.service;

import com.example.webservices.plans.domain.persistence.PlanRepository;
import com.example.webservices.shared.exception.ResourceNotFoundException;
import com.example.webservices.shared.exception.ResourceValidationException;
import com.example.webservices.subscriptions.domain.entity.Subscription;
import com.example.webservices.subscriptions.domain.persistence.SubscriptionRepository;
import com.example.webservices.subscriptions.domain.services.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private static final String ENTITY = "Subscription";
    private static final String PLAN_ENTITY = "Plan";
    private final SubscriptionRepository subscriptionRepository;
    private final PlanRepository planRepository;
    private final Validator validator;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, PlanRepository planRepository, Validator validator) {
        this.subscriptionRepository = subscriptionRepository;
        this.planRepository = planRepository;
        this.validator = validator;
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription getById(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, subscriptionId));
    }

    @Override
    public Subscription create(Long planId, Subscription request) {
        Set<ConstraintViolation<Subscription>> violations = validator.validate(request);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY, violations);
        }
        return planRepository.findById(planId).map(plan -> {
            request.setPlan(plan);
            return subscriptionRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException(PLAN_ENTITY, planId));
    }

    @Override
    public Subscription update(Long subscriptionId, Subscription request) {
        Set<ConstraintViolation<Subscription>> violations = validator.validate(request);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY, violations);
        }

        if(!subscriptionRepository.existsById(subscriptionId))
            throw new ResourceNotFoundException(ENTITY, subscriptionId);

        return subscriptionRepository.findById(subscriptionId).map(subscription ->
                subscriptionRepository.save(
                        subscription.withStartDate(request.getStartDate())
                                .withFinishDate(request.getFinishDate())
                )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, subscriptionId));

    }

    @Override
    public ResponseEntity<?> delete(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId).map(subscription -> {
            subscriptionRepository.delete(subscription);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, subscriptionId));
    }
}