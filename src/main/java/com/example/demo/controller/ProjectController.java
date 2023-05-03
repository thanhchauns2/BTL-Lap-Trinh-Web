package com.example.demo.controller;

import java.util.List;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Project;
import com.example.demo.entities.Employee;
import com.example.demo.service.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	EmployeeService employeeService = new EmployeeService();
	
	@Autowired
	ProjectService projectService = new ProjectService();
	
	@GetMapping
	public String displayProjects(Model model) {
		Iterable<Project> projects = projectService.getAll();
		model.addAttribute("projects", projects);
		return "/projects/list-projects";
	}
	
	@GetMapping("/new")
	public String addProject(Model model) {
		Project project = new Project();
		model.addAttribute("project", project);
		Iterable<Employee> allEmployees = employeeService.getAll();
		model.addAttribute("allEmployees", allEmployees);
		return "projects/new";
	}

	@PostMapping("/save")
	public String save(@Valid Project project, BindingResult result, @RequestParam("employees") List<Long> employeeIds, Model model) {
	    if (result.hasErrors()) {
//	    	System.out.println("vcl");
	    }
	    
	    List<Employee> employees = new ArrayList<>();
	    for (long id : employeeIds) {
	        employees.add(employeeService.findByEmployeeId(id));
	    }
//	    project.setEmployees(employees);
	    projectService.save(project, employees);
	    return "redirect:/projects";
	}

	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long theId, Model model) {
		Project thePrj = projectService.findByProjectId(theId);
		projectService.delete(thePrj);
		return "redirect:/projects";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("id") long theId, Model model) {
		Project thePrj = projectService.findByProjectId(theId);
		projectService.delete(thePrj);
		Project project = new Project();
		model.addAttribute("project", project);
		Iterable<Employee> allEmployees = employeeService.getAll();
		model.addAttribute("allEmployees", allEmployees);
		return "projects/new";
	}
}
