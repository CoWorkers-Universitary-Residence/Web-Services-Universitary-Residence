package com.example.webservices.Interactions.domain.entity;

import com.example.webservices.shared.domain.model.entity.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private java.util.Date date;

    @NotNull
    @NotBlank
    private String comment;

    @NotNull
    private int score;

    /*
    TODO: IMPLEMENTAR RELATIONSHIPS (DATE)
    //Relationships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false)
    private UserTenant tenant;
     */
}