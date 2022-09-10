package com.example.webservices.plans.domain.model.entity;

import com.example.webservices.subscriptions.domain.entity.Subscription;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String type;

    @NotNull
    @Column(nullable = false)
    private Float price;

    @OneToMany
    private List<Subscription> subscriptions;
}
