package com.dminc.dts.budget.tracker.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
    
    @Id
    private int id;
    private String name;

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
