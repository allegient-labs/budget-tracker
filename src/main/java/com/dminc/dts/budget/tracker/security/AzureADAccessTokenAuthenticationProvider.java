package com.dminc.dts.budget.tracker.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class AzureADAccessTokenAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            throw new AuthenticationCredentialsNotFoundException("Authorization header required");
        }

        if (!AzureADAccessTokenValidator.isValidAccessToken((String) authentication.getCredentials())) {
            throw new BadCredentialsException("Invalid Access Token");
        }

        try {
            AccessToken parsedToken = new AccessToken((String) authentication.getCredentials());
            return new AzureADAuthenticationToken(parsedToken);
        } catch (Exception e) {
            throw new BadCredentialsException("Could not parse access token");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(AzureADAuthenticationToken.class);
    }
}
