package org.example.springr2dbc.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class JwtAuthenticationConverter implements ServerAuthenticationConverter {

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono
                .justOrEmpty(exchange.getRequest().getHeaders().getFirst(AUTHORIZATION))
                .cast(String.class)
                .filter(s -> s.startsWith("Bearer "))
                .map(s -> new BearerToken(s.substring(7)));
    }
}
