package com.dminc.dts.budget.tracker.db;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dminc.dts.budget.tracker.model.DomainRangeValue;

public interface ReportRepository extends JpaRepository<DomainRangeValue, Integer> {

    @Query(nativeQuery=true, 
           value="SELECT Week AS DOMAIN, PERSON_NAME AS RANGE, ROUND(CAST(SUM(ACTUAL_HOURS) AS NUMERIC), 2) AS VALUE "+
                    "from BIG_QUERY BQ "+
                    "WHERE PROJECT_ID = :id "+
                    "GROUP BY WEEK, PERSON_NAME "+
                    "ORDER BY WEEK, PERSON_NAME")
    List<DomainRangeValue> getResourcePlanByProjectId(@Param("id") int id);


    @Query(nativeQuery=true, 
            value="SELECT Week AS DOMAIN, PERSON_NAME AS RANGE, SUM(ACTUAL_HOURS) AS VALUE "+
                     "from BIG_QUERY BQ "+
                     "WHERE PROJECT_ID = :id "+
                     "GROUP BY WEEK, PERSON_NAME "+
                     "ORDER BY WEEK, PERSON_NAME")
     List<DomainRangeValue> getResourcePlanByProjectId2(@Param("id") int id);

    
    @Query(nativeQuery=true, 
            value="SELECT month as DOMAIN, sum(actual) OVER (ORDER BY month) AS RANGE, sum(forecast) OVER (ORDER BY month) AS VALUE "+ 
                    "from "+
                    "(SELECT month, sum(actual_revenue) as actual, sum (forecast_revenue) as forecast "+
                    "from big_query  where project_id = :id group by month order by month) t")
    List<DomainRangeValue> getProjectAcutalsVsForecast(@Param("id") int id);

    @Query(nativeQuery=true, 
            value="SELECT month as DOMAIN, sum(actual) OVER (ORDER BY month) AS RANGE, sum(forecast) OVER (ORDER BY month) AS VALUE "+ 
                    "from "+
                    "(SELECT month, sum(actual_revenue) as actual, sum (forecast_revenue) as forecast "+
                    "from big_query  where project_id = :id group by month order by month) t")
    List<Map<String,String>> doQuery();
}
