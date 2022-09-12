package com.example.webservices.publications.resource.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreatePhoto {
    private Long id;

    @NotNull
    @Size(max = 500)
    private String photo;

    @NotNull
    @Size(max = 60)
    private String description;

    @NotNull
    private Long publicationId;
}