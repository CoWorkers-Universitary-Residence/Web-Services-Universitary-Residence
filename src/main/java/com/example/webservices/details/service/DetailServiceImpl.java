package com.example.webservices.details.service;

import com.example.webservices.details.domain.model.entity.Detail;
import com.example.webservices.details.domain.persistance.DetailRepository;
import com.example.webservices.details.domain.service.DetailService;
import com.example.webservices.publications.domain.model.persistance.PublicationRepository;
import com.example.webservices.shared.exception.ResourceNotFoundException;
import com.example.webservices.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class DetailServiceImpl implements DetailService {
    private static final String ENTITY = "Detail";
    private final DetailRepository detailRepository;
    private final PublicationRepository publicationRepository;
    private final Validator validator;

    public DetailServiceImpl(DetailRepository detailRepository, PublicationRepository publicationRepository, Validator validator) {
        this.detailRepository = detailRepository;
        this.publicationRepository = publicationRepository;
        this.validator = validator;
    }


    @Override
    public Page<Detail> getAllByPublicationId(Long publicationId, Pageable pageable) {
        return detailRepository.findByPublicationId(publicationId, pageable);
    }

    @Override
    public List<Detail> getAllByPublicationId(Long publicationId) {
        return detailRepository.findByPublicationId(publicationId);
    }

    @Override
    public Detail create(Long publicationId, Detail detail) {
        Set<ConstraintViolation<Detail>> violations = validator.validate(detail);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return publicationRepository.findById(publicationId).map(publication -> {
            detail.setPublication(publication);
            return detailRepository.save(detail);
        }).orElseThrow(() -> new ResourceNotFoundException("Detail", publicationId));
    }

    @Override
    public Detail update(Long publicationId, Long detailId, Detail detail) {
        Set<ConstraintViolation<Detail>> violations = validator.validate(detail);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!publicationRepository.existsById(publicationId))
            throw new ResourceNotFoundException("Publication", publicationId);

        return detailRepository.findById(detailId).map(existingDetail ->
                        detailRepository.save(existingDetail.withName(detail.getName())
                                .withDescription(detail.getDescription())))
                .orElseThrow(() -> new ResourceNotFoundException("Detail", detailId));
    }

    @Override
    public ResponseEntity<?> delete(Long publicationId, Long detailId) {
        return detailRepository.findByIdAndPublicationId(detailId, publicationId).map(detail -> {
            detailRepository.delete(detail);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, detailId));
    }
}
