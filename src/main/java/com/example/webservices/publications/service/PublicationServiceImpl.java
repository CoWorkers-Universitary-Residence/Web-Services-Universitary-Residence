package com.example.webservices.publications.service;

import com.example.webservices.publications.domain.model.entity.Publication;
import com.example.webservices.publications.domain.model.persistance.PublicationRepository;
import com.example.webservices.publications.domain.model.service.PublicationService;
import com.example.webservices.shared.exception.ResourceNotFoundException;
import com.example.webservices.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PublicationServiceImpl implements PublicationService {

    private static final String ENTITY = "Publication";
    private final PublicationRepository publicationRepository;
    private final Validator validator;

    public PublicationServiceImpl(PublicationRepository publicationRepository, Validator validator) {
        this.publicationRepository = publicationRepository;
        this.validator = validator;
    }

    @Override
    public List<Publication> getAll() {
        return publicationRepository.findAll();
    }

    @Override
    public Publication getById(Long publicationId) {
        return publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, publicationId));
    }

    @Override
    public Publication create(Publication publication) {
        Set<ConstraintViolation<Publication>> violations = validator.validate(publication);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY, violations);
        }
        return publicationRepository.save(publication);
    }

    @Override
    public Publication update(Long publicationId, Publication request) {
        Set<ConstraintViolation<Publication>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return publicationRepository.findById(publicationId).map(publication ->
            publicationRepository.save(publication.withAbout(request.getAbout())
                    .withPrice(request.getPrice())
                    .withEscrow(request.getEscrow())
                    .withExtra_expenses(request.getExtra_expenses())
                    .withGender(request.getGender())
                    .withAvailability(request.isAvailability())
                    .withRooms(request.getRooms())
                    .withBathrooms(request.getBathrooms())
                    .withWidth(request.getWidth())
                    .withHeight(request.getHeight())
                    .withCountry(request.getCountry())
                    .withCity(request.getCity())
                    .withAddress(request.getAddress())
                    .withVisit(request.isVisit())
                    .withViews(request.getViews()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, publicationId));
    }

    @Override
    public ResponseEntity<?> delete(Long publicationId) {
        return publicationRepository.findById(publicationId).map(publication -> {
            publicationRepository.delete(publication);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, publicationId));
    }
}