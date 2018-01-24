package com.dminc.dts.budget.tracker.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dminc.dts.budget.tracker.model.RowColValue;

public interface ReportRepository extends JpaRepository<RowColValue, Integer> {

    @Query(nativeQuery=true, 
           value="SELECT Week AS ROW, PERSON_NAME AS COL, SUM(ACTUAL_HOURS) AS VAL "+
                    "from BIG_QUERY BQ "+
                    "WHERE PROJECT_ID = :id "+
                    "GROUP BY WEEK, PERSON_NAME "+
                    "ORDER BY WEEK, PERSON_NAME")
    List<RowColValue> getResourcePlanByProjectId(@Param("id") int id);


    @Query(nativeQuery=true, 
            value="SELECT Week AS ROW, PERSON_NAME AS COL, SUM(ACTUAL_HOURS) AS VAL "+
                     "from BIG_QUERY BQ "+
                     "WHERE PROJECT_ID = :id "+
                     "GROUP BY WEEK, PERSON_NAME "+
                     "ORDER BY WEEK, PERSON_NAME")
     List<RowColValue> getResourcePlanByProjectId2(@Param("id") int id);

    
}
