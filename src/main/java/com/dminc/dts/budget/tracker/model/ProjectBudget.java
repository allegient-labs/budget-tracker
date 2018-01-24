package com.dminc.dts.budget.tracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="BIG_QUERY")
public class ProjectBudget {

    @Id
    @Column(name="person_id")
    private int id;

    private String projectName;

    @OneToOne(targetEntity=Project.class, fetch=FetchType.EAGER)
    @JoinColumn(name="project_id")
    private Project project;
    
    private String clientName;
    
    @OneToOne(targetEntity=Client.class, fetch=FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;
    
    private String practiceName;
    
    @OneToOne(targetEntity=Practice.class, fetch=FetchType.EAGER)
    @JoinColumn(name="practice_id")
    private Practice practice;
    private String netsuiteCode;

    private String personName;

    @OneToOne(targetEntity=Person.class, fetch=FetchType.EAGER)
    @JoinColumn(name="person_id")
    private Person person;

    private String role;
    private Double rate;
    private Double actualAllocation;
    @Column(name="FORECAST_ALLOCATION")
    private Double baselineAllocation;
    private String quarter;
    private String year;
    private String month;
    private String month_name;
    private String week;
    private String invoiceDate;
    private Date date;
    private String dateString;
    private String day;
    private Double actualHours;
    private Double actualRevenue;
    @Column(name="FORECAST_HOURS")
    private Double baselineHours;
    @Column(name="FORECAST_REVENUE")
    private Double baselineRevenue;
    private String notes;
    
    
    
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getPracticeName() {
        return practiceName;
    }
    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }
    public Practice getPractice() {
        return practice;
    }
    public void setPractice(Practice practice) {
        this.practice = practice;
    }
    public String getNetsuiteCode() {
        return netsuiteCode;
    }
    public void setNetsuiteCode(String netsuiteCode) {
        this.netsuiteCode = netsuiteCode;
    }
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Double getRate() {
        return rate;
    }
    public void setRate(Double rate) {
        this.rate = rate;
    }
    public Double getActualAllocation() {
        return actualAllocation;
    }
    public void setActualAllocation(Double actualAllocation) {
        this.actualAllocation = actualAllocation;
    }
    public Double getBaselineAllocation() {
        return baselineAllocation;
    }
    public void setBaselineAllocation(Double baselineAllocation) {
        this.baselineAllocation = baselineAllocation;
    }
    public String getQuarter() {
        return quarter;
    }
    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public String getMonth_name() {
        return month_name;
    }
    public void setMonth_name(String month_name) {
        this.month_name = month_name;
    }
    public String getWeek() {
        return week;
    }
    public void setWeek(String week) {
        this.week = week;
    }
    public String getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getDateString() {
        return dateString;
    }
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public Double getActualHours() {
        return actualHours;
    }
    public void setActualHours(Double actualHours) {
        this.actualHours = actualHours;
    }
    public Double getActualRevenue() {
        return actualRevenue;
    }
    public void setActualRevenue(Double actualRevenue) {
        this.actualRevenue = actualRevenue;
    }
    public Double getBaselineHours() {
        return baselineHours;
    }
    public void setBaselineHours(Double baselineHours) {
        this.baselineHours = baselineHours;
    }
    public Double getBaselineRevenue() {
        return baselineRevenue;
    }
    public void setBaselineRevenue(Double baselineRevenue) {
        this.baselineRevenue = baselineRevenue;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
}
