package com.example.webservices.details.domain.persistance;

import com.example.webservices.details.domain.model.entity.Detail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findByPublicationId(Long publicationId);
    Page<Detail> findByPublicationId(Long publicationId, Pageable pageable);
    Optional<Detail> findByIdAndPublicationId(Long id, Long publicationId);
}
