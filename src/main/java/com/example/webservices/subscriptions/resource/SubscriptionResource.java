package com.example.webservices.subscriptions.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SubscriptionResource {
    private Long id;
    private Date startDate;
    private Date finishDate;
    private Long planId;
}