package com.example.webservices.Interactions.resource;

import com.example.webservices.publications.resource.PublicationResource;
import com.example.webservices.users.resource.UserTenantResource;

public class ReviewResource {
    private Long id;
    private java.util.Date date;
    private String comment;
    private int score;

    //Relationships
    private PublicationResource publication;
    private UserTenantResource tenant;

}