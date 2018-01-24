package com.dminc.dts.budget.tracker.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="project_person")
public class Allocation {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @OneToOne(targetEntity=Project.class, fetch=FetchType.EAGER)
    @JoinColumn(name="project_id")
    private Project project;

    @OneToOne(targetEntity=Person.class, fetch=FetchType.EAGER)
    @JoinColumn(name="person_id")
    private Person person;

    private Double allocation;
    private Double forecastAllocation;
    private String role;
    private Double billRate;
    private Date startDate;
    private Date endDate;
    private String notes;
    @OneToOne(targetEntity=Practice.class, fetch=FetchType.EAGER)
    @JoinColumn(name="practice_id")
    private Practice practice;
    
    
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public Double getAllocation() {
        return allocation;
    }
    public void setAllocation(Double allocation) {
        this.allocation = allocation;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Double getBillRate() {
        return billRate;
    }
    public void setBillRate(Double billRate) {
        this.billRate = billRate;
    }
    public Double getForecastAllocation() {
        return forecastAllocation;
    }
    public void setForecastAllocation(Double forecastAllocation) {
        this.forecastAllocation = forecastAllocation;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Practice getPractice() {
        return practice;
    }
    public void setPractice(Practice practice) {
        this.practice = practice;
    }

    
}
