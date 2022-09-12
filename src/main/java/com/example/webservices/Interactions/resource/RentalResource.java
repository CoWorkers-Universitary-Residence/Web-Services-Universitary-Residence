package com.example.webservices.Interactions.resource;

import com.example.webservices.publications.resource.PublicationResource;
import com.example.webservices.users.resource.UserTenantResource;

public class RentalResource {
    private Long id;
    private Double price;
    private java.util.Date date;
    private java.util.Date start_date;
    private java.util.Date finish_date;
    private int months;

    //Relationships
    private PublicationResource publication;
    private UserTenantResource tenant;
}