package com.example.webservices.Interactions.domain.persistance;

import com.example.webservices.Interactions.domain.entity.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {

    List<Date> findByPublicationId(Long publicationId);
    List<Date> findByTenantId(Long tenantId);
    Optional<Date> findByIdAndPublicationIdAndTenantId(Long id, Long publicationId, Long tenantId);

}
