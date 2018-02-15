package com.dminc.dts.budget.tracker.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "google_user")
public class GoogleUser {

    @Id
    private String id;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;

    public String getLocal_id() {
        return id;
    }

    public void setLocal_id(String local_id) {
        this.id = local_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return this.roles;
    }
}
