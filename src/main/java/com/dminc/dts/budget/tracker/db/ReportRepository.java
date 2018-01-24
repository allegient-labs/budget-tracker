package com.dminc.dts.budget.tracker.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dminc.dts.budget.tracker.model.DomainRangeValue;

public interface ReportRepository extends JpaRepository<DomainRangeValue, Integer> {

    @Query(nativeQuery=true, 
           value="SELECT Week AS DOMAIN, PERSON_NAME AS RANGE, SUM(ACTUAL_HOURS) AS VALUE "+
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

    
}
