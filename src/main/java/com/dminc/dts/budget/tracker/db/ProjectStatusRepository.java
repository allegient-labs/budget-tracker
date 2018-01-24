package com.dminc.dts.budget.tracker.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dminc.dts.budget.tracker.model.ProjectStatus;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {

    @Query(nativeQuery=true,
           countQuery="select count(*) from project",
           value="SELECT  "+
            "  project_id, "+
            "  project_name,  "+
            "  P.START_DATE, "+
            "  P.END_DATE, "+
            "  P.BASE_BUDGET, "+
            "  ROUND(CAST(SUM(ACTUAL_HOURS) AS numeric), 2) as TOTAL_ACTUAL_HOURS, "+
            "  ROUND(CAST(SUM(ACTUAL_REVENUE) AS numeric), 2) AS TOTAL_ACTUAL_REVENUE, "+
            "  ROUND(CAST(SUM(case when type = 'Billed' then ACTUAL_REVENUE else 0 end) AS numeric), 2) AS ACTUAL_SPEND_TO_DATE, "+
            "  ROUND(CAST((SUM(ACTUAL_REVENUE) - P.BASE_BUDGET) AS numeric), 2) AS ACTUAL_REVENUE_DIFFERENCE, "+
            "  ROUND(CAST((SUM(ACTUAL_REVENUE) / P.BASE_BUDGET*100) AS numeric), 2) AS ACTUAL_PERCENT_DIFFERENCE, "+
            "  ROUND(CAST(SUM(FORECAST_HOURS) AS numeric), 2) as TOTAL_BASELINE_HOURS,  "+
            "  ROUND(CAST(SUM(FORECAST_REVENUE) AS numeric), 2) as TOTAL_BASELINE_REVENUE, "+
            "  ROUND(CAST(SUM(case when type = 'Billed' then FORECAST_REVENUE else 0 end) AS numeric), 2) AS BASELINE_SPEND_TO_DATE, "+
            "  ROUND(CAST((SUM(FORECAST_REVENUE) - P.BASE_BUDGET) AS numeric), 2) AS TOTAL_BASELINE_DIFFERENCE, "+
            "  ROUND(CAST((SUM(FORECAST_REVENUE) / P.BASE_BUDGET*100) AS numeric), 2) AS BASELINE_PERCENT_DIFFERENCE "+
            "FROM BIG_QUERY BQ "+
            "JOIN PROJECT P ON (BQ.PROJECT_ID = P.ID) "+
            "WHERE BQ.PROJECT_ID = :id "+
            "GROUP BY project_id, project_name, P,START_DATE, P.END_DATE, P.BASE_BUDGET "+
            "ORDER BY PROJECT_NAME")
    List<ProjectStatus> getProjectStatus(@Param("id") int projectId);
    
}
