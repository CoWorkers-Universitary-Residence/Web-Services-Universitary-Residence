package com.example.webservices.publications.domain.model.service;

import com.example.webservices.publications.domain.model.entity.Publication;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PublicationService {
    List<Publication> getAll();
    Publication getById(Long publicationId);
    Publication create(Publication publication);
    Publication update(Long publicationId, Publication request);
    ResponseEntity<?> delete(Long publicationId);

    //TODO Relation
}
