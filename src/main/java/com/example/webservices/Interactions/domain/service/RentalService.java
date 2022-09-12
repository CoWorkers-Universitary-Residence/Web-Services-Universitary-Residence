package com.example.webservices.Interactions.domain.service;

import com.example.webservices.Interactions.domain.entity.Rental;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RentalService {
    List<Rental> getAll();
    Rental getById(Long rentalId);
    List<Rental> getAllByPublicationId(Long publicationId);
    List<Rental> getAllByTenantId(Long tenantId);

    Rental create(Long publicationId, Long tenantId, Rental rental);
    Rental update(Long publicationId, Long tenantId, Long rentalId, Rental rental);
    ResponseEntity<?> delete(Long publicationId, Long tenantId, Long rentalId);

}
