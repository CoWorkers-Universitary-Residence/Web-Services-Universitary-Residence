package com.example.webservices.users.api;
import com.example.webservices.plans.resource.PlanResource;
import com.example.webservices.users.domain.service.UserOwnerService;
import com.example.webservices.users.mapping.UserOwnerMapper;
import com.example.webservices.users.resource.UserOwnerResource;
import com.example.webservices.users.resource.create.CreateUserOwnerResource;
import com.example.webservices.users.resource.update.UpdateUserOwnerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@Tag(name = "User owner", description = "CRUD owners")
@RestController
@RequestMapping("/api/v1/usersowner")
public class UserOwnerController {
    private final UserOwnerService userOwnerService;
    private final UserOwnerMapper userOwnerMapper;

    public UserOwnerController(UserOwnerService userOwnerService, UserOwnerMapper userOwnerMapper) {
        this.userOwnerService = userOwnerService;
        this.userOwnerMapper = userOwnerMapper;
    }

    @Operation(summary = "Get a owner", description = "Get a user owner stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserOwnerResource.class)
                    )}
            )
    })
    @GetMapping("/sign-in")
    public UpdateUserOwnerResource authenticateUserOwner(@RequestParam(name = "email") String email,
                                                         @RequestParam(name = "password") String password) {
        return userOwnerMapper.toResource(userOwnerService.authenticate(email, password));
    }

    @Operation(summary = "Create a owner", description = "Create a user owner in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserOwnerResource.class)
                    )}
            )
    })
    @PostMapping("/sign-up")
    public UpdateUserOwnerResource registerUserOwner(@Valid @RequestBody CreateUserOwnerResource request) {
        return userOwnerMapper.toResource(userOwnerService.register(userOwnerMapper.toModel(request)));
    }

    @Operation(summary = "Update a owner", description = "Update a user owner stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserOwnerResource.class)
                    )}
            )
    })
    @PutMapping("{userOwnerId}")
    public UpdateUserOwnerResource update(@PathVariable Long userOwnerId,
                                          @Valid @RequestBody UpdateUserOwnerResource request) {
        return userOwnerMapper.toResource(userOwnerService.update(userOwnerId, userOwnerMapper.toModel(request)));
    }

    @Operation(summary = "Delete a owner", description = "Delete a user owner stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserOwnerResource.class)
                    )}
            )
    })
    @DeleteMapping("{userOwnerId}")
    public ResponseEntity<?> delete(@PathVariable Long userOwnerId) {
        return userOwnerService.delete(userOwnerId);
    }
}