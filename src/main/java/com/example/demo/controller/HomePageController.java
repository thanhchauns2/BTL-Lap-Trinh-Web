package com.example.demo.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Project;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RequestMapping("/")
@Controller
public class HomePageController {
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@GetMapping
	public String index(Model model) {
		List<Project> projects = proService.getAll();
		model.addAttribute("projectsList", projects);
		List<Employee> employees = empService.getAll();
		model.addAttribute("employeesList", employees);
		
		Map<String, Long> projectStatusCnt = projects.stream()
	            .collect(Collectors.groupingBy(Project::getStage, Collectors.counting()));
		System.out.println(projectStatusCnt.toString());
		
//		js completed = new js("COMPLETED", projectStatusCnt.get("COMPLETED"));
//		js inprogress = new js("INPROGRESS", projectStatusCnt.get("INPROGRESS"));
//		js notstarted = new js("NOTSTARTED", projectStatusCnt.get("NOTSTARTED"));
		
//		List<js> JS = new ArrayList<>();
//		if (notstarted.getValue() != null) JS.add(notstarted);
//		if (inprogress.getValue() != null) JS.add(inprogress);
//		if (completed.getValue() != null) JS.add(completed);
		
		String json = null;
		try {
		    json = objectMapper.writeValueAsString(projectStatusCnt);
		} catch (JsonProcessingException e) {
		    e.printStackTrace();
		}

		System.out.println(json);
	    model.addAttribute("projectStatusCnt", json);
	    
		return "main/index";
	}
	
	public class js{
		public Long value;
		public String label;
		
		public js() {
			this.value = 0L;
			this.label = "";
		}
		public js(String l, Long v) {
			this.value = v;
			this.label = l;
		}
		
		public Long getValue() {
			return this.value;
		}
	}
}
