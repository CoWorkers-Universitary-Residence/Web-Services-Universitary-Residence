package com.example.webservices.users.domain.persistance;

import com.example.webservices.users.domain.model.entity.UserOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface UserOwnerRepository extends JpaRepository<UserOwner,Long> {
    Optional<UserOwner> findByEmailAndPassword(String email, String password);
    Boolean existsByNameAndLastName(String name, @NotNull @NotBlank String lastName);
    Boolean existsByEmail(String email);
}