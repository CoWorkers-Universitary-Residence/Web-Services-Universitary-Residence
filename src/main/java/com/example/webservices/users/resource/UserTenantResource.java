package com.example.webservices.users.resource;

import com.example.webservices.users.domain.model.enums.Gender;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class UserTenantResource {


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
