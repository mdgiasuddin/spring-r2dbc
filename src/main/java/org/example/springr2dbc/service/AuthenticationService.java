package org.example.springr2dbc.service;

import org.example.springr2dbc.model.dto.request.LoginRequest;
import org.example.springr2dbc.model.dto.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface AuthenticationService {
    Mono<ResponseEntity<LoginResponse>> login(LoginRequest request);
}
