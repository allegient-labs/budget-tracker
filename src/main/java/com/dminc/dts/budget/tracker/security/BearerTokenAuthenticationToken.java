package com.dminc.dts.budget.tracker.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class BearerTokenAuthenticationToken extends AbstractAuthenticationToken {

    private String token;
    private IdToken parsedToken;

    // Unauthenticated
    BearerTokenAuthenticationToken(String token) {
        super(Collections.emptyList());
        this.token = token.replace("Bearer ", "");
    }

    // Authenticated
    BearerTokenAuthenticationToken(IdToken token) {
        super(token.getGroups());
        super.setAuthenticated(true);
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

    @Override
    public boolean isAuthenticated() {
        return parsedToken != null;
    }

    @Override
    public String getName() {
        return isAuthenticated() ? getPrincipal().toString() : "";
    }
}
