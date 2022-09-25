package com.example.webservices.users.resource.create;

import com.example.webservices.Interactions.domain.entity.Date;
import com.example.webservices.users.domain.model.enums.Gender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateUserTenantResource {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    private Gender gender;

    @NotNull
    @NotBlank
    private Date date_of_birth;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String photo;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String phone_number;

    @NotNull
    @NotBlank
    private String country ;

    @NotNull
    @NotBlank
    private String city ;
}