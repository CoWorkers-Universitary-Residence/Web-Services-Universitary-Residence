package com.example.webservices.Interactions.service;

import com.example.webservices.Interactions.domain.entity.Date;
import com.example.webservices.Interactions.domain.persistance.DateRepository;
import com.example.webservices.Interactions.domain.service.DateService;
import com.example.webservices.publications.domain.model.entity.Publication;
import com.example.webservices.publications.domain.model.persistance.PublicationRepository;
import com.example.webservices.shared.exception.ResourceNotFoundException;
import com.example.webservices.shared.exception.ResourceValidationException;
import com.example.webservices.users.domain.persistance.UserTenantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DateServiceImpl implements DateService {

    private static final String ENTITY = "Date";

    private final DateRepository dateRepository;

    private final PublicationRepository publicationRepository;

    private final UserTenantRepository userTenantRepository;

    private final Validator validator;

    public DateServiceImpl(DateRepository dateRepository, PublicationRepository publicationRepository, UserTenantRepository userTenantRepository, Validator validator) {
        this.dateRepository = dateRepository;
        this.publicationRepository = publicationRepository;
        this.userTenantRepository = userTenantRepository;
        this.validator = validator;
    }

    @Override
    public List<Date> getAll() { return dateRepository.findAll(); }

    @Override
    public Date getById(Long dateId) {
        return dateRepository.findById(dateId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, dateId));
    }

    @Override
    public List<Date> getAllByPublicationId(Long publicationId) {
        return dateRepository.findByPublicationId(publicationId);
    }

    @Override
    public List<Date> getAllByTenantId(Long tenantId) {
        return dateRepository.findByTenantId(tenantId);
    }

    @Override
    public Date create(Long publicationId, Long tenantId, Date date) {
        Set<ConstraintViolation<Date>> violations = validator.validate(date);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!publicationRepository.existsById(publicationId))
            throw new ResourceNotFoundException("Publication", publicationId);

        Optional<Publication> publicationExisting = publicationRepository.findById(publicationId);

        return userTenantRepository.findById(tenantId).map(tenant -> {
            date.setPublication(publicationExisting.get());
            date.setTenant(tenant);
            return dateRepository.save(date);
        }).orElseThrow(() -> new ResourceNotFoundException("Tenant", tenantId));
    }

    @Override
    public Date update(Long publicationId, Long tenantId, Long dateId, Date date) {
        Set<ConstraintViolation<Date>> violations = validator.validate(date);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!publicationRepository.existsById(publicationId))
            throw new ResourceNotFoundException("Publication", publicationId);

        if(!userTenantRepository.existsById(tenantId))
            throw new ResourceNotFoundException("Tenant", tenantId);

        return dateRepository.findById(dateId).map(existingDate ->
                dateRepository.save(
                        existingDate.withStart_date(date.getStart_date())
                                .withMonths(date.getMonths())
                                .withPhone_number(date.getPhone_number())
                                .withEmail(date.getEmail())
                                .withDescription(date.getDescription())
                                .withStatus(date.isStatus())
                )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, dateId));

    }

    @Override
    public ResponseEntity<?> delete(Long publicationId, Long tenantId, Long dateId) {
        return dateRepository.findByIdAndPublicationIdAndTenantId(dateId, publicationId, tenantId).map(date -> {
            dateRepository.delete(date);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, dateId));
    }
}