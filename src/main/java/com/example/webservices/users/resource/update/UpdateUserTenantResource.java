package com.example.webservices.users.resource.update;

import com.example.webservices.Interactions.domain.entity.Date;
import com.example.webservices.users.domain.model.enums.Gender;

public class UpdateUserTenantResource {
    private String name;
    private String lastName;
    private Gender gender;
    private Date date_of_birth;
    private String description;
    private String photo;
    private String email;
    private String password;
    private String phone_number;
    private String country ;
    private String city ;
}