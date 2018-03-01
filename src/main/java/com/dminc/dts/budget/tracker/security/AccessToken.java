package com.dminc.dts.budget.tracker.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class AccessToken {

    private String email;
    private List<GrantedAuthority> groups;

    AccessToken(String credentials) throws Exception {
        DecodedJWT decodedJWT = JWT.decode(credentials);

        this.setEmail(decodedJWT.getClaim("email").asString());

        List<String> groups = decodedJWT.getClaim("groups").asList(String.class);
        List<GrantedAuthority> authorities = new ArrayList<>();
        groups.forEach(g -> authorities.add(new SimpleGrantedAuthority(g)));
        this.setGroups(authorities);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GrantedAuthority> getGroups() {
        return groups;
    }

    public void setGroups(List<GrantedAuthority> groups) {
        this.groups = groups;
    }

}
