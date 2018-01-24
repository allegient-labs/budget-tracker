package com.dminc.dts.budget.tracker.db;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dminc.dts.budget.tracker.model.ProjectBudget;

@RepositoryRestResource(collectionResourceRel = "budget", path = "budgets")
public interface ProjectBudgetRepository extends PagingAndSortingRepository<ProjectBudget, Integer> {

    List<ProjectBudget> findByProjectId(@Param("project_id") int projectId);
    
}
