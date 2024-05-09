package org.example.springr2dbc.controller;

import lombok.RequiredArgsConstructor;
import org.example.springr2dbc.model.dto.request.LoginRequest;
import org.example.springr2dbc.model.dto.response.LoginResponse;
import org.example.springr2dbc.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public Mono<ResponseEntity<LoginResponse>> login(@RequestBody LoginRequest request) {
        return authenticationService.login(request);
    }
}
