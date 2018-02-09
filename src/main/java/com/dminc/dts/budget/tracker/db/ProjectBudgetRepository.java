package com.dminc.dts.budget.tracker.db;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dminc.dts.budget.tracker.model.ProjectBudget;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RepositoryRestResource(collectionResourceRel = "budget", path = "budgets")
public interface ProjectBudgetRepository extends PagingAndSortingRepository<ProjectBudget, Integer> {

    List<ProjectBudget> findByProjectId(@Param("project_id") int projectId);
    
}
