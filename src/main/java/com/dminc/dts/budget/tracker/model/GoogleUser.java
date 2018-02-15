package com.dminc.dts.budget.tracker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "google_user")
public class GoogleUser {

    @Id
    private String id;
    private String email;

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
}
