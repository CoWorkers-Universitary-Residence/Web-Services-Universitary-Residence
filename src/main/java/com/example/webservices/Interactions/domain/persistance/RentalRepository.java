package com.example.webservices.Interactions.domain.persistance;

import com.example.webservices.Interactions.domain.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByPublicationId(Long publicationId);
    List<Rental> findByTenantId(Long tenantId);
    Optional<Rental> findByIdAndPublicationIdAndTenantId(Long id, Long publicationId, Long tenantId);
}
