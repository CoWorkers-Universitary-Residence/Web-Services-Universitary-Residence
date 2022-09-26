package com.example.webservices.Interactions.api;

import com.example.webservices.Interactions.domain.service.DateService;
import com.example.webservices.Interactions.mapping.DateMapper;
import com.example.webservices.Interactions.resource.DateResource;
import com.example.webservices.Interactions.resource.create.CreateDateResource;
import com.example.webservices.Interactions.resource.update.UpdateDateResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@Tag(name = "Publication / Dates", description = "CRUD Dates")
@RestController
@RequestMapping("api/v1/publications/{publicationId}/dates")
public class PublicationDateController {

    private final DateService dateService;

    private final DateMapper mapper;

    public PublicationDateController(DateService dateService, DateMapper mapper) {
        this.dateService = dateService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<DateResource> getAllByPublicationId(@PathVariable Long publicationId, Pageable pageable) {
        return mapper.modelListPage(dateService.getAllByPublicationId(publicationId), pageable);
    }

    @PostMapping("tenantId={tenantId}")
    public DateResource createDate(@PathVariable Long publicationId, @PathVariable Long tenantId, @RequestBody CreateDateResource resource) {
        return mapper.toResource(dateService.create(publicationId, tenantId, mapper.toModel(resource)));
    }

    @PutMapping("tenantId={tenantId}/{dateId}")
    public DateResource updateDate(@PathVariable Long dateId, @PathVariable Long tenantId, @PathVariable Long publicationId, @RequestBody UpdateDateResource resource) {
        return mapper.toResource(dateService.update(publicationId, tenantId, dateId, mapper.toModel(resource)));
    }

    @DeleteMapping("tenantId={tenantId}/{dateId}")
    public ResponseEntity<?> deleteDate(@PathVariable Long dateId, @PathVariable Long tenantId, @PathVariable Long publicationId) {
        return dateService.delete(publicationId, tenantId, dateId);
    }

}
