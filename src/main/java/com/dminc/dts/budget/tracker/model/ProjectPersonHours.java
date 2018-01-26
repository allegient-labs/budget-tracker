package com.dminc.dts.budget.tracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="project_person_hours")
public class ProjectPersonHours {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @OneToOne(targetEntity=Person.class, fetch=FetchType.EAGER)
    @JoinColumn(name="person_id")
    private Person person;

    @OneToOne(targetEntity=Project.class, fetch=FetchType.EAGER)
    @JoinColumn(name="project_id")
    private Project project;
    
    private Date date;
    private float hours;
    @Column(name="notes")
    private String description;
    
    
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public float getHours() {
        return hours;
    }
    public void setHours(float hours) {
        this.hours = hours;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
