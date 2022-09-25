package com.example.webservices.users.domain.persistance;

import com.example.webservices.users.domain.model.entity.UserOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserOwnerRepository extends JpaRepository<UserOwner,Long> {
    Optional<UserOwner> findByEmailAndPassword(String email, String password);
    Boolean existsByNameAndLastName(String name);
    Boolean existsByEmail(String email);
}