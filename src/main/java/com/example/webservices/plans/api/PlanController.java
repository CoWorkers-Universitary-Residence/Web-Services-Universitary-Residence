package com.example.webservices.plans.api;

import com.example.webservices.plans.domain.services.PlanService;
import com.example.webservices.plans.mapping.PlanMapper;
import com.example.webservices.plans.resource.CreatePlanResource;
import com.example.webservices.plans.resource.PlanResource;
import com.example.webservices.plans.resource.UpdatePlanResource;
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
@Tag(name = "Plan", description = "CRUD planes")
@RestController
@RequestMapping("/api/v1/plans")
public class PlanController {
    @Autowired
    private PlanService planService;

    @Autowired
    private PlanMapper mapper;

    @Operation(summary = "Get All plans", description = "Get all plans stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plans found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlanResource.class)
                    )}
            )
    })
    @GetMapping
    public List<PlanResource> getAllPlans(){
        return mapper.modelListToPage(planService.getAll());
    }

    @Operation(summary = "Get a plan", description = "Get a plan stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlanResource.class)
                    )}
            )
    })
    @GetMapping("{planId}")
    public PlanResource getPlanById(@PathVariable("planId") Long planId) {
        return mapper.toResource(planService.getById(planId));
    }

    @Operation(summary = "Create a plan", description = "Create a plan in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlanResource.class)
                    )}
            )
    })
    @PostMapping
    public PlanResource createPlan(@RequestBody CreatePlanResource request){
        return mapper.toResource(planService.create(mapper.toModel(request)));
    }

    @Operation(summary = "Update a plan", description = "Update a plan in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlanResource.class)
                    )}
            )
    })
    @PutMapping("{planId}")
    public PlanResource updatePlan(@PathVariable Long planId, @RequestBody UpdatePlanResource request){
        return mapper.toResource(planService.update(planId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete a plan", description = "Delete a plan in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlanResource.class)
                    )}
            )
    })
    @DeleteMapping("{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable Long planId){
        return planService.delete(planId);
    }
}
