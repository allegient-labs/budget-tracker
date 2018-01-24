package com.dminc.dts.budget.tracker.db;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dminc.dts.budget.tracker.model.ProjectPersonHours;

@RepositoryRestResource(collectionResourceRel = "timelogs", path = "timelogs")
public interface ProjectPersonHoursRepository extends PagingAndSortingRepository<ProjectPersonHours, Integer> {

    ProjectPersonHours findById(@Param("id") Integer id);
    
    List<ProjectPersonHours> findByProjectId(@Param("project_id") Integer id);

    List<ProjectPersonHours> findByPersonId(@Param("person_id") Integer id);

}
