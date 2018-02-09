package com.dminc.dts.budget.tracker.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.dminc.dts.budget.tracker.model.Allocation;
import com.dminc.dts.budget.tracker.model.Client;
import com.dminc.dts.budget.tracker.model.Holiday;
import com.dminc.dts.budget.tracker.model.Person;
import com.dminc.dts.budget.tracker.model.PersonTimeOff;
import com.dminc.dts.budget.tracker.model.Practice;
import com.dminc.dts.budget.tracker.model.Project;
import com.dminc.dts.budget.tracker.model.ProjectBudget;
import com.dminc.dts.budget.tracker.model.ProjectPersonHours;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        List<Class<?>> repoClasses = Arrays.asList(
                Allocation.class, Client.class, Holiday.class, Person.class, PersonTimeOff.class, Practice.class,
                Project.class, ProjectBudget.class, ProjectPersonHours.class);
        repoClasses.stream().forEach(c -> config.exposeIdsFor(c));
    }
}