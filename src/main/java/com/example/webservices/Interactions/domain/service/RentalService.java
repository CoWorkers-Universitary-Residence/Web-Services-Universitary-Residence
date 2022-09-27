package com.example.webservices.Interactions.domain.service;

import com.example.webservices.Interactions.domain.entity.Rental;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RentalService {
    List<Rental> getAll();
    Rental getById(Long rentalId);
    List<Rental> getAllByDateId(Long dateId);

    Rental create(Long dateId, Rental rental);
    Rental update(Long dateId, Long rentalId, Rental rental);
    ResponseEntity<?> delete(Long dateId, Long rentalId);

}
