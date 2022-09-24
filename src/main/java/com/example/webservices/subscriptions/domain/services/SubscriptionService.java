package com.example.webservices.subscriptions.domain.services;

import com.example.webservices.subscriptions.domain.entity.Subscription;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getAll();
    Subscription getById(Long subscriptionId);
    Subscription create(Long planId, Subscription request);
    Subscription update(Long subscriptionId, Subscription request);
    ResponseEntity<?> delete(Long subscriptionId);
}
