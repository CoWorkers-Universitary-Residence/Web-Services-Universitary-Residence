package com.example.webservices.subscriptions.api;

import com.example.webservices.subscriptions.domain.services.SubscriptionService;
import com.example.webservices.subscriptions.mapping.SubscriptionMapper;
import com.example.webservices.subscriptions.resource.SubscriptionResource;
import com.example.webservices.subscriptions.resource.create.CreateSubscription;
import com.example.webservices.subscriptions.resource.update.UpdateSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SubscriptionMapper mapper;

    @GetMapping
    public List<SubscriptionResource> getAllSubscriptions(){
        return mapper.modelListToPage(subscriptionService.getAll());
    }

    @GetMapping("{subscriptionId}")
    public SubscriptionResource getSubscriptionById(@PathVariable("subscriptionId") Long subscriptionId) {
        return mapper.toResource(subscriptionService.getById(subscriptionId));
    }

    @PostMapping
    public SubscriptionResource createSubscription(@RequestBody CreateSubscription request){
        return mapper.toResource(subscriptionService.create(request.getPlanId(), mapper.toModel(request)));
    }

    @PutMapping("{subscriptionId}")
    public SubscriptionResource updateSubscription(@PathVariable Long subscriptionId, @RequestBody UpdateSubscription request){
        return mapper.toResource(subscriptionService.update(subscriptionId, mapper.toModel(request)));
    }

    @DeleteMapping("{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long subscriptionId){
        return subscriptionService.delete(subscriptionId);
    }
}