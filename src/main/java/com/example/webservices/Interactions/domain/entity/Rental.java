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
@Table(name = "rentals")
public class Rental extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    //Relationships
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "date_id", nullable = false)
    private Date c_date;
}