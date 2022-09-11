package com.example.webservices.publications.resource.update;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdatePublication {
    private Long id;

    @NotNull
    @Size(max = 700)
    private String about;

    @NotNull
    private double price;

    @NotNull
    private double escrow;

    @NotNull
    @Size(max = 40)
    private String extra_expenses;

    @NotNull
    @Size(max = 10)
    private String gender;

    @NotNull
    private boolean availability;

    @NotNull
    private int rooms;

    @NotNull
    @Size(max = 15)
    private String bathrooms;

    @NotNull
    private float width;

    @NotNull
    private float height;

    @NotNull
    @Size(max = 30)
    private String country;

    @NotNull
    @Size(max = 30)
    private String city;

    @NotNull
    @Size(max = 100)
    private String address;

    @NotNull
    private boolean visit;

    @NotNull
    private int views;

    //TODO Relation with owner
}