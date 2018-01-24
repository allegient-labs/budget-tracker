package com.dminc.dts.budget.tracker.db;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dminc.dts.budget.tracker.model.PersonTimeOff;

@RepositoryRestResource(collectionResourceRel = "timeoffs", path = "timeoffs")
public interface PersonTimeOffRepository extends PagingAndSortingRepository<PersonTimeOff, Integer> {

    List<PersonTimeOff> findByPersonId(@Param("person_id") Integer id);
    
}
