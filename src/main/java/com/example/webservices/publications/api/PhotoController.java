package com.example.webservices.publications.api;

import com.example.webservices.publications.domain.model.service.PhotoService;
import com.example.webservices.publications.mapping.PhotoMapper;
import com.example.webservices.publications.resource.PhotoResource;
import com.example.webservices.publications.resource.create.CreatePhoto;
import com.example.webservices.publications.resource.update.UpdatePhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private PhotoMapper mapper;

    @GetMapping("{publicationId}")
    public PhotoResource getPhotoByPublicationId(@PathVariable("publicationId") Long publicationId) {
        return mapper.toResource(photoService.getByPublicationId(publicationId));
    }

    @PostMapping
    public PhotoResource createPhoto(@RequestBody CreatePhoto request){
        return mapper.toResource(photoService.create(request.getPublicationId(), mapper.toModel(request)));
    }

    @PutMapping("{photoId}")
    public PhotoResource updatePhoto(@PathVariable Long photoId, @RequestBody UpdatePhoto request){
        return mapper.toResource(photoService.update(request.getPublicationId(), photoId, mapper.toModel(request)));
    }

    @DeleteMapping("{photoId}")
    public ResponseEntity<?> deletePhoto(@PathVariable Long photoId){
        return photoService.delete(photoId);
    }
}
