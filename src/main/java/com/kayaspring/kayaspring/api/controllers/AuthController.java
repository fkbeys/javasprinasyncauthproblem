package com.kayaspring.kayaspring.api.controllers;

import com.kayaspring.kayaspring.business.services.AuthenticationService;
import com.kayaspring.kayaspring.common.GenericResultClass;
import com.kayaspring.kayaspring.entities.requests.LoginRequest;
import com.kayaspring.kayaspring.entities.requests.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;

    }


    @PostMapping("/signin")
    public CompletableFuture<GenericResultClass> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return CompletableFuture.supplyAsync(() -> {
            return authenticationService.authenticateUser(loginRequest);
        });
    }

    @PostMapping("/signup")
    public CompletableFuture<GenericResultClass> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return CompletableFuture.supplyAsync(() -> {
            return authenticationService.registerUser(signUpRequest);
        });

    }
}