package com.example.webservices.Interactions.api;

import com.example.webservices.Interactions.domain.service.DateService;
import com.example.webservices.Interactions.mapping.DateMapper;
import com.example.webservices.Interactions.resource.DateResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get All dates", description = "Get all dates stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dates found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DateResource.class))})
    })
    @GetMapping
    public Page<DateResource> getAllDates(Pageable pageable)
    {
        return mapper.modelListPage(dateService.getAll(), pageable);
    }

    @Operation(summary = "Get a date", description = "Get a date by id stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Date found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DateResource.class))})
    })
    @GetMapping("{dateId}")
    public DateResource getDateById(@PathVariable Long dateId) {
        return mapper.toResource(dateService.getById(dateId));
    }

    @Operation(summary = "Get all dates by tenantId", description = "Get all dates by tenantId stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dates found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DateResource.class))})
    })
    @GetMapping("tenantId={tenantId}")
    public Page<DateResource> getAllByTenantId(@PathVariable Long tenantId, Pageable pageable) {
        return mapper.modelListPage(dateService.getAllByTenantId(tenantId), pageable);
    }

}
