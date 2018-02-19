package com.dminc.dts.budget.tracker.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class AadBearerTokenAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            throw new AuthenticationCredentialsNotFoundException("Authorization header required");
        }

        if (!JwtHelper.isValidJwt((String)authentication.getCredentials())) {
            throw new BadCredentialsException("Invalid Bearer Token");
        }

        IdToken parsedToken = JwtHelper.parseToken((String)authentication.getCredentials());
        return new BearerTokenAuthenticationToken(parsedToken);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(BearerTokenAuthenticationToken.class);
    }
}
