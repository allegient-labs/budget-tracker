package com.dminc.dts.budget.tracker.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class IdToken {

    private String email;
    private List<GrantedAuthority> groups;

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
