package com.example.webservices.publications.api;

import com.example.webservices.publications.resource.create.CreatePublication;
import com.example.webservices.publications.resource.update.UpdatePublication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.webservices.publications.domain.model.service.PublicationService;
import com.example.webservices.publications.mapping.PublicationMapper;
import com.example.webservices.publications.resource.PublicationResource;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@Tag(name = "Publication", description = "CRUD publications")
@RestController
@RequestMapping("/api/v1/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private PublicationMapper mapper;

    @Operation(summary = "Get All publications", description = "Get all publications stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publications found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicationResource.class)
                    )}
            )
    })
    @GetMapping
    public List<PublicationResource> getAllPublications(){
        return mapper.modelListToPage(publicationService.getAll());
    }

    @Operation(summary = "Get a publication by Id", description = "Get a publications stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicationResource.class)
                    )}
            )
    })
    @GetMapping("{publicationId}")
    public PublicationResource getPublicationById(@PathVariable("publicationId") Long publicationId) {
        return mapper.toResource(publicationService.getById(publicationId));
    }

    @Operation(summary = "Create a publication", description = "Create a publication in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicationResource.class)
                    )}
            )
    })
    @PostMapping
    public PublicationResource createPublication(@RequestBody CreatePublication request){
        return mapper.toResource(publicationService.create(mapper.toModel(request)));
    }

    @Operation(summary = "Update a publication", description = "Update a publication in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicationResource.class)
                    )}
            )
    })
    @PutMapping("{publicationId}")
    public PublicationResource updatePublication(@PathVariable Long publicationId, @RequestBody UpdatePublication request){
        return mapper.toResource(publicationService.update(publicationId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete a publication", description = "Delete a publication in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicationResource.class)
                    )}
            )
    })
    @DeleteMapping("{publicationId}")
    public ResponseEntity<?> deletePost(@PathVariable Long publicationId){
        return publicationService.delete(publicationId);
    }
}