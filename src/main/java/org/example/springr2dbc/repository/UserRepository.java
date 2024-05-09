package org.example.springr2dbc.repository;

import org.example.springr2dbc.model.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<User, Integer> {
    Mono<User> findByUsername(String username);
}
