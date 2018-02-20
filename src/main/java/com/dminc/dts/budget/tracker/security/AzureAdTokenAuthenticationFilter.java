package com.dminc.dts.budget.tracker.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AzureAdTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationManager authenticationManager;

    AzureAdTokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(x -> !x.getMethod().equals("OPTIONS"));  // Authenticate all requests
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            throw new AuthenticationCredentialsNotFoundException("Bearer Token Required");
        }
        return authenticationManager.authenticate(new BearerTokenAuthenticationToken(authorizationHeader));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

}
