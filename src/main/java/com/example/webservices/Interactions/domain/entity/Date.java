package com.example.webservices.Interactions.domain.entity;

import com.example.webservices.publications.domain.model.entity.Publication;
import com.example.webservices.shared.domain.model.entity.AuditModel;
import com.example.webservices.users.domain.model.entity.UserTenant;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dates")
public class Date extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private java.util.Date start_date;

    @NotNull
    private int months;

    @NotNull
    private int phone_number;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String description;

    private boolean status;

    //Relationships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false)
    private UserTenant tenant;

}