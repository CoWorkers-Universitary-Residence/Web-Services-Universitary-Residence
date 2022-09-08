package com.example.webservices.publications.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationResource {
    private Long id;
    private String about;
    private double price;
    private double escrow;
    private String extra_expenses;
    private String gender;
    private boolean availability;
    private int rooms;
    private String bathrooms;
    private float width;
    private float height;
    private String country;
    private String city;
    private String address;
    private boolean visit;
    private int views;

    //TODO Relation with owner
}