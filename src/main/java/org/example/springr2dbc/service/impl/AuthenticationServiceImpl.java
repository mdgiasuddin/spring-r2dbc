package org.example.springr2dbc.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springr2dbc.config.security.JwtService;
import org.example.springr2dbc.model.dto.request.LoginRequest;
import org.example.springr2dbc.model.dto.response.LoginResponse;
import org.example.springr2dbc.model.entity.User;
import org.example.springr2dbc.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final ReactiveUserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<ResponseEntity<LoginResponse>> login(LoginRequest request) {
        Mono<UserDetails> userMono = userDetailsService.findByUsername(request.username())
                .defaultIfEmpty(new User());

        return userMono.flatMap(user -> {
            if (user.getUsername() == null) {
                return Mono.just(ResponseEntity.status(UNAUTHORIZED).body(new LoginResponse()));
            }
            if (passwordEncoder.matches(request.password(), user.getPassword())) {
                String accessToken = jwtService.generateAccessToken(user);
                return Mono.just(ResponseEntity.ok(new LoginResponse(accessToken)));
            }
            return Mono.just(ResponseEntity.status(UNAUTHORIZED).body(new LoginResponse()));
        });
    }
}
