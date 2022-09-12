package com.example.webservices.Interactions.resource.update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateReviewResource {
    private Long id;

    @NotNull
    @NotBlank
    private java.util.Date date;

    @NotNull
    @NotBlank
    private String comment;

    @NotNull
    private int score;
}
