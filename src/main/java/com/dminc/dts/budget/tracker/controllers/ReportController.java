package com.dminc.dts.budget.tracker.controllers;

import com.dminc.dts.budget.tracker.db.ProjectStatusRepository;
import com.dminc.dts.budget.tracker.db.ReportRepository;
import com.dminc.dts.budget.tracker.model.DomainRangeResult;
import com.dminc.dts.budget.tracker.model.DomainRangeValue;
import com.dminc.dts.budget.tracker.model.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
//@PreAuthorize("hasAuthority('object id for group')")
public class ReportController {

    @Autowired
    ReportRepository reports;
    @Autowired
    ProjectStatusRepository projectStatusRepository;

    @RequestMapping("/resource-plan/{project_id}")
    public List<DomainRangeValue> getResourcePlan(@PathVariable("project_id") int id) {
        return reports.getResourcePlanByProjectId(id);
    }

    @RequestMapping("/project-status/{project_id}")
    public List<ProjectStatus> getProjectStatus(@PathVariable("project_id") int id) {
        return projectStatusRepository.getProjectStatus(id);
    }
    
    @RequestMapping("/pretty-resource-plan/{project_id}")
    public DomainRangeResult getPrettyResourcePlan(@PathVariable("project_id") int id) {
        List<DomainRangeValue> domainData = reports.getResourcePlanByProjectId(id);
        return new DomainRangeResult.Builder(domainData).domainKey("week").build();
    }

    @RequestMapping("/actuals-vs-forecast/{project_id}")
    public DomainRangeResult getActualsVsForecast(@PathVariable("project_id") int id) {
        List<DomainRangeValue> domainData = reports.getProjectAcutalsVsForecast(id);
        return new DomainRangeResult.Builder(domainData).domainKey("month").build();
    }


}
