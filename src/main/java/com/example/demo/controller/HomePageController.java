package com.example.demo.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.*;
import com.example.demo.service.*;
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
	
	@GetMapping("/index")
	public String index(Model model) {
		List<Project> projects = proService.getAll();
		model.addAttribute("projectsList", projects);
		List<Employee> employees = empService.getAll();
		model.addAttribute("employeesList", employees);
		
		Map<String, Long> projectStatusCnt = projects.stream()
	            .collect(Collectors.groupingBy(Project::getStage, Collectors.counting()));
		System.out.println(projectStatusCnt.toString());
		
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
	
	@GetMapping("/about")
	public String about(Model model) {
		List<Employee> employees = empService.getAll();
		for(Employee emp : employees) {
			if(emp.getActive() == 1) { // meaning user was logined
				if(emp.getRole().equals("admin")) { //meaning employee is an admin
					return "main/About/about";
				}
				if(emp.getRole().equals("user")) { // meaning employee is an user
					return "main/About/aboutUSer";
				}
			}
		}
		return "main/About/aboutNotlogin";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "main/signup";
	}
	
	@PostMapping("/signed")
	public String signed(Model model, @Valid Employee employee, Errors errors, @RequestParam("role") String role) {
		if(errors.hasErrors())
			return "main/homeNotlogin";
		employee.setRole(role);
		empService.save(employee);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "main/login";
	}
	
	@PostMapping("/checkLogin")
	public String checklogin(Model model,@RequestParam("username") String username, @RequestParam("pass") String pass) {
				
		List<Employee> employees = empService.getAll();
		for(Employee emp : employees) {
			if(emp.getUser().equals(username)&&emp.getPassword().equals(pass)) {
				empService.activeOn(emp.getEmployeeId());
			}
			else {
				empService.activeOff(emp.getEmployeeId());
			}
		}
		return "redirect:/";
	}
	
	@GetMapping("/infor")
	public String infor(Model model) {
		List<Employee> employees = empService.getAll();
		for(Employee emp : employees) {
			if(emp.getActive() == 1) { // meaning user was logined
				model.addAttribute("employee", emp);
				if(emp.getRole().equals("admin")) { //meaning employee is an admin
					return "main/Infor/informationAdmin";
				}
				if(emp.getRole().equals("user")) { // meaning employee is an user
					return "main/Infor/information";
				}
			}
		}
		return "redirect:/";
	}
	
	
	@GetMapping("/logout")
	public String logout(Model model) {
		List<Employee> employees = empService.getAll();
		for(Employee emp : employees) {
			if(emp.getActive() == 1) {
				empService.activeOff(emp.getEmployeeId());
			}
		}
		return "redirect:/";
	}
	
	@GetMapping
	public String home(Model model) {
		List<Employee> employees = empService.getAll();
		for(Employee emp : employees) {
			if(emp.getActive() == 1) { // meaning user was logined
				if(emp.getRole().equals("admin")) { //meaning employee is an admin
					return "main/Home/home";
				}
				if(emp.getRole().equals("user")) { // meaning employee is an user
					return "main/Home/homeUSer";
				}
			}
		}
		return "main/Home/homeNotlogin";
	}
	
	
}
