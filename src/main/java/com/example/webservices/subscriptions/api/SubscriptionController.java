package com.example.webservices.subscriptions.api;

import com.example.webservices.subscriptions.domain.services.SubscriptionService;
import com.example.webservices.subscriptions.mapping.SubscriptionMapper;
import com.example.webservices.subscriptions.resource.SubscriptionResource;
import com.example.webservices.subscriptions.resource.create.CreateSubscription;
import com.example.webservices.subscriptions.resource.update.UpdateSubscription;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@Tag(name = "Subscription", description = "CRUD subscriptions")
@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SubscriptionMapper mapper;

    @Operation(summary = "Get All subscriptions", description = "Get all subscriptions stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscriptions found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResource.class)
                    )}
            )
    })
    @GetMapping
    public List<SubscriptionResource> getAllSubscriptions(){
        return mapper.modelListToPage(subscriptionService.getAll());
    }

    @Operation(summary = "Get a subscription", description = "Get a subscription stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResource.class)
                    )}
            )
    })
    @GetMapping("{subscriptionId}")
    public SubscriptionResource getSubscriptionById(@PathVariable("subscriptionId") Long subscriptionId) {
        return mapper.toResource(subscriptionService.getById(subscriptionId));
    }

    @Operation(summary = "Create a subscription", description = "Create a subscription stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResource.class)
                    )}
            )
    })
    @PostMapping
    public SubscriptionResource createSubscription(@RequestBody CreateSubscription request){
        return mapper.toResource(subscriptionService.create(request.getPlanId(), mapper.toModel(request)));
    }

    @Operation(summary = "Update a subscription", description = "Update a subscription stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResource.class)
                    )}
            )
    })
    @PutMapping("{subscriptionId}")
    public SubscriptionResource updateSubscription(@PathVariable Long subscriptionId, @RequestBody UpdateSubscription request){
        return mapper.toResource(subscriptionService.update(subscriptionId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete a subscription", description = "Delete a subscription stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResource.class)
                    )}
            )
    })
    @DeleteMapping("{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long subscriptionId){
        return subscriptionService.delete(subscriptionId);
    }
}