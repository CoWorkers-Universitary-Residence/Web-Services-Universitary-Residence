package com.example.webservices.details.domain.service;

import com.example.webservices.details.domain.model.entity.Detail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DetailService {
   Page<Detail> getAllByPublicationId(Long publicationId, Pageable pageable);
   List<Detail> getAllByPublicationId(Long publicationId);
   Detail create(Long publicationId, Detail detail);
   Detail update(Long publicationId, Long detailId, Detail detail);
   ResponseEntity<?> delete(Long publicationId, Long detailId);
}
