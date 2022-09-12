package com.example.webservices.publications.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoResource {
    private Long id;
    private String photo;
    private String description;
    private Long publicationId;
}