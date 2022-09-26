package com.example.webservices.Interactions.resource.create;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateDateResource {
    @NotNull
    @NotBlank
    private java.util.Date start_date;

    @NotNull
    private int months;

    @NotNull
    private int phone_number;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String description;

    private boolean status;
}