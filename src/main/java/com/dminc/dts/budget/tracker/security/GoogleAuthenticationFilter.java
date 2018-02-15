package com.dminc.dts.budget.tracker.security;

import com.dminc.dts.budget.tracker.db.GoogleUserRepository;
import com.dminc.dts.budget.tracker.model.GoogleUser;
import com.google.identitytoolkit.GitkitUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoogleAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    GoogleUserRepository userRepository;

    private AuthenticationManager authenticationManager;
    private static final String LOGIN_URL = "/callback";

    GoogleAuthenticationFilter(AuthenticationManager authenticationManager, GoogleUserRepository userRepository) {
        super(x -> !x.getRequestURI().equals(LOGIN_URL));  // Don't authenticate requests to the login url
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Cookie gtoken = WebUtils.getCookie(request, "gtoken");
        return authenticationManager.authenticate(new GTokenAuthenticationToken(gtoken));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        GitkitUser principal = (GitkitUser)authResult.getPrincipal();

        // Check if the logged in user exists in the database
        GoogleUser gUser = userRepository.findGoogleUserById(principal.getLocalId());

        if (gUser == null) {
            GoogleUser newUser = new GoogleUser();
            newUser.setEmail(principal.getEmail());
            newUser.setLocal_id(principal.getLocalId());

            userRepository.save(newUser);
        }

        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendRedirect("http://localhost:8080/callback");
    }
}
