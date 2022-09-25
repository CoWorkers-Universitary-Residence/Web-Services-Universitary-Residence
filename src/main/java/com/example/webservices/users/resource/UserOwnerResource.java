package com.example.webservices.users.resource;

import com.example.webservices.Interactions.domain.entity.Date;
import com.example.webservices.users.domain.model.enums.Gender;
import com.example.webservices.users.domain.model.enums.Status;

public class UserOwnerResource {
    private Long id;
    private String name;
    private String lastName;
    private Gender gender;
    private Date date_of_birth;
    private String description;
    private String email;
    private String password;
    private String phone_number;
    private String photo;
    private Status status;
    private Long score;
    private String country ;
    private String city ;
    private String address ;
}
