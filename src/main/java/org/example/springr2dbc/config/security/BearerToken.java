package org.example.springr2dbc.config.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import static org.springframework.security.core.authority.AuthorityUtils.NO_AUTHORITIES;

public class BearerToken extends AbstractAuthenticationToken {

    private final String token;

    public BearerToken(String token) {
        super(NO_AUTHORITIES);
        this.token = token;
    }

    @Override
    public String getCredentials() {
        return token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }
}
