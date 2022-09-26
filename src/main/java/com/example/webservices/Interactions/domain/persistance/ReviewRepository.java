package com.example.webservices.Interactions.domain.persistance;

import com.example.webservices.Interactions.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    /*
    List<Review> findByPublicationId(Long publicationId);
    List<Review> findByTenantId(Long tenantId);
    Optional<Review> findByIdAndPublicationIdAndTenantId(Long id, Long publicationId, Long tenantId);*/
}
