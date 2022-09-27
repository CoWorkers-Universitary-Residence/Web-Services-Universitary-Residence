package com.example.webservices.Interactions.api;

import com.example.webservices.Interactions.domain.service.DateService;
import com.example.webservices.Interactions.mapping.DateMapper;
import com.example.webservices.Interactions.resource.DateResource;
import com.example.webservices.Interactions.resource.create.CreateDateResource;
import com.example.webservices.Interactions.resource.update.UpdateDateResource;
import com.example.webservices.users.resource.UserTenantResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all dates by publicationId", description = "Get a all dates by publicationId stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dates found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DateResource.class)
                    )}
            )
    })
    @GetMapping
    public Page<DateResource> getAllByPublicationId(@PathVariable Long publicationId, Pageable pageable) {
        return mapper.modelListPage(dateService.getAllByPublicationId(publicationId), pageable);
    }

    @Operation(summary = "Create a date by tenantId", description = "Create a date by tenantId in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dates found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DateResource.class)
                    )}
            )
    })
    @PostMapping("tenantId={tenantId}")
    public DateResource createDate(@PathVariable Long publicationId, @PathVariable Long tenantId, @RequestBody CreateDateResource resource) {
        return mapper.toResource(dateService.create(publicationId, tenantId, mapper.toModel(resource)));
    }

    @Operation(summary = "Update a date", description = "Update a date by tenantId in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Date updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DateResource.class)
                    )}
            )
    })
    @PutMapping("tenantId={tenantId}/{dateId}")
    public DateResource updateDate(@PathVariable Long dateId, @PathVariable Long tenantId, @PathVariable Long publicationId, @RequestBody UpdateDateResource resource) {
        return mapper.toResource(dateService.update(publicationId, tenantId, dateId, mapper.toModel(resource)));
    }

    @Operation(summary = "Delete a date", description = "Delete a date by tenantId in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Date deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DateResource.class)
                    )}
            )
    })
    @DeleteMapping("tenantId={tenantId}/{dateId}")
    public ResponseEntity<?> deleteDate(@PathVariable Long dateId, @PathVariable Long tenantId, @PathVariable Long publicationId) {
        return dateService.delete(publicationId, tenantId, dateId);
    }

}
