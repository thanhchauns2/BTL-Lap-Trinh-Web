package com.example.demo.controller;

import java.util.List;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String displayProjects() {
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
	public String save(Project project, @RequestParam List<Long> employees, Model model) {
		List<Employee> includingEmployees = new ArrayList<>();
		for (long id : employees) {
			includingEmployees.add(employeeService.findByEmployeeId(id));
		}
		projectService.save(project, includingEmployees);
//		projectService.save(project);
		return "redirect:/employees";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee() {
		return "redirect:/employees";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm() {
		return "employees/new.html";
	}
}
