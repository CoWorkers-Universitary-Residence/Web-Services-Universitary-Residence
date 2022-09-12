package com.example.webservices.publications.service;

import com.example.webservices.publications.domain.model.entity.Photo;
import com.example.webservices.publications.domain.model.persistance.PhotoRepository;
import com.example.webservices.publications.domain.model.persistance.PublicationRepository;
import com.example.webservices.publications.domain.model.service.PhotoService;
import com.example.webservices.shared.exception.ResourceNotFoundException;
import com.example.webservices.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class PhotoServiceImpl implements PhotoService {

    private static final String ENTITY = "Photo";
    private final PhotoRepository photoRepository;
    private final PublicationRepository publicationRepository;
    private final Validator validator;

    public PhotoServiceImpl(PhotoRepository photoRepository, PublicationRepository publicationRepository, Validator validator) {
        this.photoRepository = photoRepository;
        this.publicationRepository = publicationRepository;
        this.validator = validator;
    }

    @Override
    public Photo getByPublicationId(Long publicationId) {
        return photoRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, publicationId));
    }

    @Override
    public Photo create(Long publicationId, Photo request) {
        Set<ConstraintViolation<Photo>> violations = validator.validate(request);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY, violations);
        }
        return publicationRepository.findById(publicationId).map(publication -> {
            request.setPublication(publication);
            return photoRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("Publication", publicationId));
    }

    @Override
    public Photo update(Long publicationId, Long photoId, Photo request) {
        Set<ConstraintViolation<Photo>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!publicationRepository.existsById(publicationId))
            throw new ResourceNotFoundException("Publication", publicationId);

        return photoRepository.findById(photoId).map(photo ->
                photoRepository.save(photo.withPhoto(request.getPhoto())
                        .withDescription(request.getDescription()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, photoId));
    }

    @Override
    public ResponseEntity<?> delete(Long photoId) {
        return photoRepository.findById(photoId).map(photo -> {
            photoRepository.delete(photo);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, photoId));
    }
}