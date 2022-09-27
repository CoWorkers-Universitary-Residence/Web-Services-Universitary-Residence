package com.example.webservices.users.domain.persistance;

import com.example.webservices.users.domain.model.entity.UserTenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTenantRepository  extends JpaRepository<UserTenant, Long> {
    Optional<UserTenant> findByEmailAndPassword(String email, String password);
    Boolean existsByNameAndLastName(String name, String lastname);
    Boolean existsByEmail(String email);
}