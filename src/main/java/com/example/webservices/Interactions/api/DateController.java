package com.example.webservices.Interactions.api;

import com.example.webservices.Interactions.domain.service.DateService;
import com.example.webservices.Interactions.mapping.DateMapper;
import com.example.webservices.Interactions.resource.DateResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@Tag(name = "Date", description = "Read Dates")
@RestController
@RequestMapping("api/v1/dates")
public class DateController {

    private final DateService dateService;

    private final DateMapper mapper;

    public DateController(DateService dateService, DateMapper mapper) {
        this.dateService = dateService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<DateResource> getAllDates(Pageable pageable)
    {
        return mapper.modelListPage(dateService.getAll(), pageable);
    }

    @GetMapping("{dateId}")
    public DateResource getDateById(@PathVariable Long dateId) {
        return mapper.toResource(dateService.getById(dateId));
    }

    @GetMapping("tenantId={tenantId}")
    public Page<DateResource> getAllByTenantId(@PathVariable Long tenantId, Pageable pageable) {
        return mapper.modelListPage(dateService.getAllByTenantId(tenantId), pageable);
    }

}
