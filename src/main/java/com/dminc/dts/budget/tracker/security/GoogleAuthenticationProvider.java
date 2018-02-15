package com.dminc.dts.budget.tracker.security;

import com.google.identitytoolkit.GitkitClient;
import com.google.identitytoolkit.GitkitUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
public class GoogleAuthenticationProvider implements AuthenticationProvider {

    private static final String PRIVATE_KEY_PATH = "C:\\Users\\cgrove\\dev\\budget-tracker\\src\\main\\resources\\allegient-budget-tracker-47d80f185ee7.p12";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof GTokenAuthenticationToken)) {
            throw new BadCredentialsException("Expecting Authentication of type GTokenAuthenticationToken");
        }

        try {
            GitkitClient gitkitClient = GitkitClient.newBuilder()
                    .setServerApiKey("AIzaSyAr8-ysc8Vw9hQQbPzuvIolEM1k3MK7WKs")
                    .setGoogleClientId("allegient-budget-tracker")
                    .setServiceAccountEmail("cgrove.dmi@gmail.com")
                    .setKeyStream(new FileInputStream(PRIVATE_KEY_PATH))
                    .setWidgetUrl("/callback")
                    .setCookieName("gtoken")
                    .build();

            String token = authentication.getCredentials().toString();
            GitkitUser principal = gitkitClient.validateToken(token);
            return new GTokenAuthenticationToken(principal);
        } catch (Exception e) {
            throw new BadCredentialsException("Error Parsing token");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(GTokenAuthenticationToken.class);
    }
}
