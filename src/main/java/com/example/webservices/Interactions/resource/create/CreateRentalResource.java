package com.example.webservices.Interactions.resource.create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateRentalResource {

    @NotNull
    private Double price;

    @NotNull
    @NotBlank
    private java.util.Date date;

    @NotNull
    @NotBlank
    private java.util.Date start_date;

    @NotNull
    @NotBlank
    private java.util.Date finish_date;

    @NotNull
    private int months;
}