package com.dminc.dts.budget.tracker.db;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dminc.dts.budget.tracker.model.Practice;

@RepositoryRestResource(collectionResourceRel = "practices", path = "practices")
public interface PracticeRepository extends PagingAndSortingRepository<Practice, Integer> {

}
