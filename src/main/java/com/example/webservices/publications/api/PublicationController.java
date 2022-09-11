package com.example.webservices.publications.api;

import com.example.webservices.publications.resource.create.CreatePublication;
import com.example.webservices.publications.resource.update.UpdatePublication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.webservices.publications.domain.model.service.PublicationService;
import com.example.webservices.publications.mapping.PublicationMapper;
import com.example.webservices.publications.resource.PublicationResource;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private PublicationMapper mapper;

    @GetMapping
    public List<PublicationResource> getAllPublications(){
        return mapper.modelListToPage(publicationService.getAll());
    }

    @GetMapping("{publicationId}")
    public PublicationResource getPublicationById(@PathVariable("publicationId") Long publicationId) {
        return mapper.toResource(publicationService.getById(publicationId));
    }

    @PostMapping
    public PublicationResource createPublication(@RequestBody CreatePublication request){
        return mapper.toResource(publicationService.create(mapper.toModel(request)));
    }

    @PutMapping("{publicationId}")
    public PublicationResource updatePublication(@PathVariable Long publicationId, @RequestBody UpdatePublication request){
        return mapper.toResource(publicationService.update(publicationId, mapper.toModel(request)));
    }

    @DeleteMapping("{publicationId}")
    public ResponseEntity<?> deletePost(@PathVariable Long publicationId){
        return publicationService.delete(publicationId);
    }
}