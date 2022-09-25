package com.example.webservices.users.domain.service;

import com.example.webservices.users.domain.model.entity.UserOwner;
import org.springframework.http.ResponseEntity;

public interface UserOwnerService {
    UserOwner authenticate(String email, String password);
    UserOwner register(UserOwner request);
    UserOwner update(Long userId, UserOwner request);
    ResponseEntity<?> delete(Long userId);
}
