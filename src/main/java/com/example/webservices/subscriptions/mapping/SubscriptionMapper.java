package com.example.webservices.subscriptions.mapping;

import com.example.webservices.shared.mapping.EnhancedModelMapper;
import com.example.webservices.subscriptions.domain.entity.Subscription;
import com.example.webservices.subscriptions.resource.SubscriptionResource;
import com.example.webservices.subscriptions.resource.create.CreateSubscription;
import com.example.webservices.subscriptions.resource.update.UpdateSubscription;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class SubscriptionMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public SubscriptionResource toResource(Subscription model) {
        return mapper.map(model, SubscriptionResource.class);
    }

    public List<SubscriptionResource> modelListToPage(List<Subscription> modelList){
        return mapper.mapList(modelList, SubscriptionResource.class);
    }

    public Subscription toModel(CreateSubscription resource) {
        return mapper.map(resource, Subscription.class);
    }

    public Subscription toModel(UpdateSubscription resource) {
        return mapper.map(resource, Subscription.class);
    }
}