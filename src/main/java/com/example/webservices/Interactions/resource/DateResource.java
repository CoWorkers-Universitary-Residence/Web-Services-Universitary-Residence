package com.example.webservices.Interactions.resource;

import com.example.webservices.publications.resource.PublicationResource;
import com.example.webservices.users.resource.UserTenantResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class DateResource {
    private Long id;
    private java.util.Date start_date;
    private int months;
    private int phone_number;
    private String email;
    private String description;

    //Relationships
    private PublicationResource publication;
    private UserTenantResource tenant;
}