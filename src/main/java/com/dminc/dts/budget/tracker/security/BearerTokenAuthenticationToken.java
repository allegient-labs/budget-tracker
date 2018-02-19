package com.dminc.dts.budget.tracker.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class BearerTokenAuthenticationToken extends AbstractAuthenticationToken {

    private String token;
    private IdToken parsedToken;

    // Unauthenticated
    public BearerTokenAuthenticationToken(String token) {
        super(Collections.emptyList());
        this.token = token.replace("Bearer ", "");
    }

    // Authenticated
    public BearerTokenAuthenticationToken(IdToken token) {
        super(token.getGroups());
        this.parsedToken = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return parsedToken == null ? null : parsedToken.getEmail();
    }
}
