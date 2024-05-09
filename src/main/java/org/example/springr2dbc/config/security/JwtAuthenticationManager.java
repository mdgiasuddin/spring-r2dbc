package org.example.springr2dbc.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final ReactiveUserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono
                .justOrEmpty(authentication)
                .cast(BearerToken.class)
                .flatMap(auth -> {
                    String username = jwtService.extractUsername(auth.getPrincipal());
                    Mono<UserDetails> userMono = userDetailsService.findByUsername(username).defaultIfEmpty(new AppUserDetails());

                    return userMono.flatMap(user -> {
                        if (user.getUsername().equals("INVALID_USER") || !user.isEnabled()) {
                            return Mono.empty();
                        }

                        if (jwtService.isTokenValid(auth.getPrincipal())) {
                            return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities()));
                        }
                        return Mono.empty();
                    });
                });
    }
}
