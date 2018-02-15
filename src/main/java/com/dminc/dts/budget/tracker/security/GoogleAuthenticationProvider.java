package com.dminc.dts.budget.tracker.security;

import com.dminc.dts.budget.tracker.db.GoogleUserRepository;
import com.dminc.dts.budget.tracker.model.GoogleUser;
import com.google.identitytoolkit.GitkitClient;
import com.google.identitytoolkit.GitkitUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleAuthenticationProvider implements AuthenticationProvider {

    private static final String PRIVATE_KEY_PATH = "C:\\Users\\cgrove\\dev\\budget-tracker\\src\\main\\resources\\allegient-budget-tracker-47d80f185ee7.p12";

    @Autowired
    GoogleUserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof GTokenAuthenticationToken)) {
            throw new BadCredentialsException("Expecting Authentication of type GTokenAuthenticationToken");
        }

        try {
            GitkitClient gitkitClient = GitkitClient.newBuilder()
                    .setServerApiKey("[Redacted]")
                    .setGoogleClientId("allegient-budget-tracker")
                    .setServiceAccountEmail("cgrove.dmi@gmail.com")
                    .setKeyStream(new FileInputStream(PRIVATE_KEY_PATH))
                    .setWidgetUrl("/callback")
                    .setCookieName("gtoken")
                    .build();

            String token = authentication.getCredentials().toString();
            GitkitUser principal = gitkitClient.validateToken(token);

            List<GrantedAuthority> roles = new ArrayList<>();
            if (isReturningUser(principal)) {
                GoogleUser user = userRepository.findGoogleUserById(principal.getLocalId());
                user.getRoles().forEach(r -> roles.add(new SimpleGrantedAuthority(r.getName())));
            }
            GTokenAuthenticationToken authenticationToken = new GTokenAuthenticationToken(principal, roles);

            return authenticationToken;
        } catch (Exception e) {
            throw new BadCredentialsException("Error Parsing token");
        }
    }

    private boolean isReturningUser(GitkitUser user) {
        return userRepository.findGoogleUserById(user.getLocalId()) != null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(GTokenAuthenticationToken.class);
    }
}
