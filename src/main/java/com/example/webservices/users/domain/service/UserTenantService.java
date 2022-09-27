package com.example.webservices.users.domain.service;

import com.example.webservices.users.domain.model.entity.UserTenant;
import org.springframework.http.ResponseEntity;

public interface UserTenantService {
    UserTenant authenticate(String email, String password);
    UserTenant register(UserTenant request);
    UserTenant update(Long userId, UserTenant request);
    ResponseEntity<?> delete(Long userId);

}
