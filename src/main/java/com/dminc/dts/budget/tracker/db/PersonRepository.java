package com.dminc.dts.budget.tracker.db;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dminc.dts.budget.tracker.model.Person;

@RepositoryRestResource(collectionResourceRel = "person", path = "persons")
public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {

   
}
