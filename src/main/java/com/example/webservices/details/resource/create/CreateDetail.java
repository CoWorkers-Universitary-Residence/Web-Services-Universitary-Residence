package com.example.webservices.details.resource.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateDetail {
    @NotNull
    @Size(max = 40)
    private String name;
    @NotNull
    @Size(max = 700)
    private String description;
}
