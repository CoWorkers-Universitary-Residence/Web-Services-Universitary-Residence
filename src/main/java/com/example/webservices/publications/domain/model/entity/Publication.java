package com.example.webservices.publications.domain.model.entity;

import lombok.*;
import com.example.webservices.shared.domain.model.entity.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publications")
public class Publication extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 700)
    private String about;

    @NotNull
    private double price;

    @NotNull
    private double escrow;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String extra_expenses;

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String gender;

    @NotNull
    private boolean availability;

    @NotNull
    private int rooms;

    @NotNull
    @NotBlank
    @Size(max = 15)
    private String bathrooms;

    @NotNull
    private float width;

    @NotNull
    private float height;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String country;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String city;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String address;

    @NotNull
    private boolean visit;

    @NotNull
    private int views;

    //TODO Relation with owner
}
