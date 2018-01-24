package com.dminc.dts.budget.tracker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(DomainRangeValue.class)
public class DomainRangeValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="DOMAIN", insertable=false, updatable=false)
    private String domain;
    
    @Id
    @Column(name="RANGE", insertable=false, updatable=false)
    private String range;
    
    @Id
       @Column(name="VALUE", insertable=false, updatable=false)
    private String value;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String val) {
        this.value = val;
    }

}
