package com.dminc.dts.budget.tracker.db;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dminc.dts.budget.tracker.model.Project;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RepositoryRestResource(collectionResourceRel = "project", path = "projects")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer> {
    Project findById(@Param("id") Integer id);   
}
