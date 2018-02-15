package com.dminc.dts.budget.tracker.security;

import com.google.identitytoolkit.GitkitUser;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import javax.servlet.http.Cookie;
import java.util.Collections;

public final class GTokenAuthenticationToken extends AbstractAuthenticationToken {

    private String token = null;
    private GitkitUser principal = null;

    GTokenAuthenticationToken(Cookie cookie) {
        super(Collections.emptyList());
        this.token = (cookie == null) ? "" : cookie.getValue();
    }

    GTokenAuthenticationToken(GitkitUser principal) {
        super(Collections.emptyList());
        this.principal = principal;
    }

    @Override
    public boolean isAuthenticated() {
        return principal != null;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
