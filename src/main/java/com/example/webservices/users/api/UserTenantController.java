package com.example.webservices.users.api;
import com.example.webservices.users.domain.service.UserTenantService;
import com.example.webservices.users.mapping.UserTenantMapper;
import com.example.webservices.users.resource.create.CreateUserTenantResource;
import com.example.webservices.users.resource.update.UpdateUserTenantResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/api/v1/userTenants-tenant")
@CrossOrigin
public class UserTenantController {
    private final UserTenantService userTenantService;
    private final UserTenantMapper userTenantMapper;

    public UserTenantController(UserTenantService userTenantService, UserTenantMapper userTenantMapper) {
        this.userTenantService = userTenantService;
        this.userTenantMapper = userTenantMapper;
    }

    @GetMapping("/sign-in")
    public UpdateUserTenantResource authenticateUserTenant(@RequestParam(name = "email") String email,
                                                           @RequestParam(name = "password") String password) {
        return userTenantMapper.toResource(userTenantService.authenticate(email, password));
    }

    @PostMapping("/sign-up")
    public UpdateUserTenantResource registerUserTenant(@Valid @RequestBody CreateUserTenantResource request) {
        return userTenantMapper.toResource(userTenantService.register(userTenantMapper.toModel(request)));
    }

    @PutMapping("{userTenantId}")
    public UpdateUserTenantResource update(@PathVariable Long userTenantId,
                                           @Valid @RequestBody UpdateUserTenantResource request) {
        return userTenantMapper.toResource(userTenantService.update(userTenantId, userTenantMapper.toModel(request)));
    }

    @DeleteMapping("{userTenantId}")
    public ResponseEntity<?> delete(@PathVariable Long userTenantId) {
        return userTenantService.delete(userTenantId);
    }
}