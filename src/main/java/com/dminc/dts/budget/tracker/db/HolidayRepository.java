package com.dminc.dts.budget.tracker.db;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dminc.dts.budget.tracker.model.Holiday;

@RepositoryRestResource(collectionResourceRel = "holidays", path = "holidays")
public interface HolidayRepository extends PagingAndSortingRepository<Holiday, Integer> {

}
