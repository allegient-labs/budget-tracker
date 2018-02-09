package com.dminc.dts.budget.tracker.db;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dminc.dts.budget.tracker.model.Allocation;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RepositoryRestResource(collectionResourceRel = "assignments", path = "assignments")
public interface AllocationRepository extends PagingAndSortingRepository<Allocation, Integer> {

    Allocation findById(@Param("id") Integer id);

    List<Allocation> findByProjectId(@Param("project_id") Integer id);

    List<Allocation> findByPersonId(@Param("person_id") Integer id);

    List<Allocation> findByPersonIdAndProjectId(@Param("person_id") Integer personId, @Param("project_id") Integer projectId);

}
