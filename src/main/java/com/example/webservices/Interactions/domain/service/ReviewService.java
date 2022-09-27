package com.example.webservices.Interactions.domain.service;

import com.example.webservices.Interactions.domain.entity.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {

    List<Review> getAll();
    Review getById(Long reviewId);
    List<Review> getAllByPublicationId(Long publicationId);
    List<Review> getAllByTenantId(Long tenantId);

    Review create(Long publicationId, Long tenantId, Review review);
    Review update(Long publicationId, Long tenantId, Long reviewId, Review review);
    ResponseEntity<?> delete(Long publicationId, Long tenantId, Long reviewId);

}
