package com.example.webservices.plans.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdatePlanResource {
    private Long id;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    private Float price;
}
