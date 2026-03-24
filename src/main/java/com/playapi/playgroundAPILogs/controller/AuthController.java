package com.playapi.playgroundAPILogs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.playapi.playgroundAPILogs.dto.LoginResponse;
import com.playapi.playgroundAPILogs.dto.SignupResponse;
import com.playapi.playgroundAPILogs.model.LoginRequest;
import com.playapi.playgroundAPILogs.model.SignupRequest;
import com.playapi.playgroundAPILogs.service.AuthService;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
        private final AuthService authService;

        public AuthController(AuthService authService){
            this.authService = authService;
        }
           /// login api
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody(required = false) LoginRequest request) {
        String emptyStateMessage = authService.CheckEmptyState(request);
        String email = request != null ? request.getEmail() : null;

        if (emptyStateMessage != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                         .body(new LoginResponse(400, emptyStateMessage, email, LocalDateTime.now()));
        }
        boolean ok = authService.authenticateUser(request);

        if (ok) {
            return ResponseEntity.ok(new LoginResponse(200, "User authenticated", email, LocalDateTime.now()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                         .body(new LoginResponse(401, "Invalid username or password", email, LocalDateTime.now()));
    }


    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody(required = false) SignupRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest()
                    .body(new SignupResponse(400, "Invalid request", null));
        }

        String registrationMessage = authService.UserRegistractionProcess(request); //GOOD
        boolean success = "User registered successfully".equals(registrationMessage);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        SignupResponse response = new SignupResponse(status.value(), registrationMessage, request.getEmail());
        return ResponseEntity.status(status).body(response);
    }

}
