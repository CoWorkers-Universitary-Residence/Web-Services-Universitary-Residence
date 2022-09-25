package com.example.webservices.users.resource.create;

import com.example.webservices.users.domain.model.enums.Gender;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class CreateUserOwnerResource {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    private Gender gender;

    @NotNull
    private Date date_of_birth;

    @NotNull
    @NotBlank
    private String description;

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
    private String photo;

    @NotNull
    @NotBlank
    private String country ;

    @NotNull
    @NotBlank
    private String city ;

    @NotNull
    @NotBlank
    private String address ;
}