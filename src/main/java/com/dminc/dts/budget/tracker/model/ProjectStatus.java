package com.dminc.dts.budget.tracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/*
            value="SELECT  "+
                 "  project_id, "+
                 "  project_name,  "+
                 "  P.START_DATE, "+
                 "  P.END_DATE, "+
                 "  P.BASE_BUDGET, "+
                 "  ROUND(SUM(ACTUAL_HOURS)::numeric, 2) as TOTAL_ACTUAL_HOURS, "+
                 "  ROUND(SUM(ACTUAL_REVENUE)::numeric, 2) AS TOTAL_ACTUAL_REVENUE "+
                 "  ROUND(SUM(case when type = 'Billed' then ACTUAL_REVENUE else 0 end)::numeric, 2) AS ACTUAL_SPEND_TO_DATE, "+
                 "  ROUND((SUM(ACTUAL_REVENUE) - P.BASE_BUDGET)::numeric, 2) AS ACTUAL_REVENUE_DIFFERENCE, "+
                 "  ROUND((SUM(ACTUAL_REVENUE) / P.BASE_BUDGET*100)::numeric, 2) AS ACTUAL_PERCENT_DIFFERENCE, "+
                 "  ROUND(SUM(FORECAST_HOURS)::numeric, 2) as TOTAL_BASELINE_HOURS,  "+
                 "  ROUND(SUM(FORECAST_REVENUE)::numeric, 2) as TOTAL_BASELINE_REVENUE, "+
                 "  ROUND(SUM(case when type = 'Billed' then FORECAST_REVENUE else 0 end)::numeric, 2) AS BASELINE_SPEND_TO_DATE, "+
                 "  ROUND((SUM(FORECAST_REVENUE) - P.BASE_BUDGET)::numeric, 2) AS BASELINE_DIFFERENCE, "+
                 "  ROUND((SUM(FORECAST_REVENUE) / P.BASE_BUDGET*100)::numeric, 2) AS BASELINE_PCT_DIFFERENCE "+
                 "FROM BIG_QUERY BQ "+
                 "JOIN PROJECT P ON (BQ.PROJECT_ID = P.ID) "+
                 "GROUP BY project_id, project_name, P,START_DATE, P.END_DATE, P.BASE_BUDGET "+
                 "ORDER BY PROJECT_NAME")
 */

@Entity
public class ProjectStatus {

    @Id
    @Column(name="project_id")
    private int id;
    @Column(name="project_name")
    private String projectName;
    private Date startDate;
    private Date endDate;
    private double baseBudget;
    private long totalActualHours;
    private double totalActualRevenue;
    private double actualSpendToDate;
    private double actualRevenueDifference;
    private double actualPercentDifference;

    private long totalBaselineHours;
    private double totalBaselineRevenue;
    private double baselineSpendToDate;
    private double totalBaselineDifference;
    private double baselinePercentDifference;
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public double getBaseBudget() {
        return baseBudget;
    }
    public void setBaseBudget(double baseBudget) {
        this.baseBudget = baseBudget;
    }
    public long getTotalActualHours() {
        return totalActualHours;
    }
    public void setTotalActualHours(long totalActualHours) {
        this.totalActualHours = totalActualHours;
    }
    public double getTotalActualRevenue() {
        return totalActualRevenue;
    }
    public void setTotalActualRevenue(double totalActualRevenue) {
        this.totalActualRevenue = totalActualRevenue;
    }
    public double getActualSpendToDate() {
        return actualSpendToDate;
    }
    public void setActualSpendToDate(double actualSpendToDate) {
        this.actualSpendToDate = actualSpendToDate;
    }
    public double getActualRevenueDifference() {
        return actualRevenueDifference;
    }
    public void setActualRevenueDifference(double actualRevenueDifference) {
        this.actualRevenueDifference = actualRevenueDifference;
    }
    public double getActualPercentDifference() {
        return actualPercentDifference;
    }
    public void setActualPercentDifference(double actualPercentDifference) {
        this.actualPercentDifference = actualPercentDifference;
    }
    public long getTotalBaselineHours() {
        return totalBaselineHours;
    }
    public void setTotalBaselineHours(long totalBaselineHours) {
        this.totalBaselineHours = totalBaselineHours;
    }
    public double getTotalBaselineRevenue() {
        return totalBaselineRevenue;
    }
    public void setTotalBaselineRevenue(double totalBaselineRevenue) {
        this.totalBaselineRevenue = totalBaselineRevenue;
    }
    public double getBaselineSpendToDate() {
        return baselineSpendToDate;
    }
    public void setBaselineSpendToDate(double baselineSpendToDate) {
        this.baselineSpendToDate = baselineSpendToDate;
    }
    public double getTotalBaselineDifference() {
        return totalBaselineDifference;
    }
    public void setTotalBaselineDifference(double totalBaselineDifference) {
        this.totalBaselineDifference = totalBaselineDifference;
    }
    public double getBaselinePercentDifference() {
        return baselinePercentDifference;
    }
    public void setBaselinePercentDifference(double baselinePercentDifference) {
        this.baselinePercentDifference = baselinePercentDifference;
    }
    
}
