package com.example.webservices.users.api;
import com.example.webservices.users.domain.service.UserOwnerService;
import com.example.webservices.users.mapping.UserOwnerMapper;
import com.example.webservices.users.resource.create.CreateUserOwnerResource;
import com.example.webservices.users.resource.update.UpdateUserOwnerResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/usersowner")
@CrossOrigin
public class UserOwnerController {
    private final UserOwnerService userOwnerService;
    private final UserOwnerMapper userOwnerMapper;

    public UserOwnerController(UserOwnerService userOwnerService, UserOwnerMapper userOwnerMapper) {
        this.userOwnerService = userOwnerService;
        this.userOwnerMapper = userOwnerMapper;
    }

    @GetMapping("/sign-in")
    public UpdateUserOwnerResource authenticateUserOwner(@RequestParam(name = "email") String email,
                                                         @RequestParam(name = "password") String password) {
        return userOwnerMapper.toResource(userOwnerService.authenticate(email, password));
    }

    @PostMapping("/sign-up")
    public UpdateUserOwnerResource registerUserOwner(@Valid @RequestBody CreateUserOwnerResource request) {
        return userOwnerMapper.toResource(userOwnerService.register(userOwnerMapper.toModel(request)));
    }

    @PutMapping("{userOwnerId}")
    public UpdateUserOwnerResource update(@PathVariable Long userOwnerId,
                                          @Valid @RequestBody UpdateUserOwnerResource request) {
        return userOwnerMapper.toResource(userOwnerService.update(userOwnerId, userOwnerMapper.toModel(request)));
    }

    @DeleteMapping("{userOwnerId}")
    public ResponseEntity<?> delete(@PathVariable Long userOwnerId) {
        return userOwnerService.delete(userOwnerId);
    }
}