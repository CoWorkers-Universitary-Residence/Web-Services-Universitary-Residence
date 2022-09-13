package com.example.webservices.details.api;
import com.example.webservices.details.domain.service.DetailService;
import com.example.webservices.details.mapping.DetailMapper;
import com.example.webservices.details.resource.DetailResource;
import com.example.webservices.details.resource.create.CreateDetail;
import com.example.webservices.details.resource.update.UpdateDetail;
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

@CrossOrigin("*")
@Tag(name = "Publication / Details", description = "Read, create, update and delete details by publication Id")
@RestController
@RequestMapping("api/v1/publications/{publicationId}/details")
public class PublicationDetailController {
    private final DetailService detailService;
    private final DetailMapper mapper;


    public PublicationDetailController(DetailService detailService, DetailMapper mapper) {
        this.detailService = detailService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All details", description = "Get all details by publicationId stored in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetailResource.class))})
    })
    @GetMapping
    public Page<DetailResource> getAllDetailsByPublicationId(@PathVariable Long publicationId, Pageable pageable) {
        return mapper.modelListToPage(detailService.getAllByPublicationId(publicationId), pageable);
    }

    @Operation(summary = "Create a detail", description = "Create a detail by publicationId in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detail created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetailResource.class))})
    })
    @PostMapping
    public DetailResource createDetail(@PathVariable Long publicationId, @RequestBody CreateDetail resource) {
        return mapper.toResource(detailService.create(publicationId, mapper.toModel(resource)));
    }

    @Operation(summary = "Update a detail", description = "Update a detail in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detail updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetailResource.class))})
    })
    @PutMapping("{detailId}")
    public DetailResource updateDetail(@PathVariable Long detailId,
                                         @PathVariable Long publicationId, @RequestBody UpdateDetail resource) {
        return mapper.toResource(detailService.update(publicationId, detailId, mapper.toModel(resource)));
    }

    @Operation(summary = "Delete a detail", description = "Delete a detail from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detail deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("{detailId}")
    public ResponseEntity<?> deleteDetail(@PathVariable Long publicationId, @PathVariable Long detailId) {
        return detailService.delete(publicationId, detailId);
    }



}
