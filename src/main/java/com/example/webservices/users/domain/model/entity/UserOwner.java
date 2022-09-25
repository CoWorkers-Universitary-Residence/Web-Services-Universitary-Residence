package com.example.webservices.users.domain.model.entity;

import com.example.webservices.users.domain.model.enums.Gender;
import com.example.webservices.users.domain.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
@Entity
@Table(name = "owners")
public class UserOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Status status;

    @NotNull
    @NotBlank
    private Long score;

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