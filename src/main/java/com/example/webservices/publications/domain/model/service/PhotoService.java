package com.example.webservices.publications.domain.model.service;

import com.example.webservices.publications.domain.model.entity.Photo;
import org.springframework.http.ResponseEntity;

public interface PhotoService {
    Photo getByPublicationId(Long publicationId);
    Photo create(Long publicationId, Photo request);
    Photo update(Long publicationId, Long photoId, Photo request);
    ResponseEntity<?> delete(Long photoId);
}
