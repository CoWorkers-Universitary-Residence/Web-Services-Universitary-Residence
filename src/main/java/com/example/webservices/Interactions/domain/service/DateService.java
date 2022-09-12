package com.example.webservices.Interactions.domain.service;

import com.example.webservices.Interactions.domain.entity.Date;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DateService {
    List<Date> getAll();
    Date getById(Long dateId);
    List<Date> getAllByPublicationId(Long publicationId);
    List<Date> getAllByTenantId(Long tenantId);

    Date create(Long publicationId, Long tenantId, Date date);
    Date update(Long publicationId, Long tenantId, Long dateId, Date date);
    ResponseEntity<?> delete(Long publicationId, Long tenantId, Long dateId);

}
