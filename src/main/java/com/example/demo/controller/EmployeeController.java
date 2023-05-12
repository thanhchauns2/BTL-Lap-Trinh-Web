package com.example.demo.controller;

import java.util.List;

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

@RequestMapping("/employees")
@Controller
public class EmployeeController {
	
	@Autowired
    private EmployeeService employeeService;
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping
	public String home(Model model) {
		Iterable<Employee> employees = employeeService.getAll();
		model.addAttribute("employees", employees);
		return "/employees/employees.html";
	}
	
	@GetMapping("/new")
	public String add_new(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employees/add-new.html";
	}
	
	@PostMapping("/save")
	public String save(Model model, @Valid Employee employee, Errors errors, @RequestParam("role") String role) {
		if(errors.hasErrors())
			return "employees/new-employee";
		employee.setRole(role);
		employeeService.save(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		Employee theEmp = employeeService.findByEmployeeId(theId);
		employeeService.delete(theEmp);
		return "redirect:/employees";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
		Employee theEmp = employeeService.findByEmployeeId(theId);
		employeeService.delete(theEmp);
		model.addAttribute("employee", theEmp);
		return "employees/add-new.html";
	}

	@GetMapping("/changepwd")
	public String changePassword(@RequestParam("id") long Id, Model model) {
		Employee theEmp = employeeService.findByEmployeeId(Id);
		model.addAttribute("employee", theEmp);
		if(theEmp.getRole().equals("admin")) { //meaning employee is an admin
			return "Pass/changepwdAdmin";
		}
		return "Pass/changepwdUser"; // meaning user
	}
	
	@PostMapping("/savepwd")
	public String savePassword(Model model,@RequestParam("username") String user,@RequestParam("pass") String pass, @RequestParam("id") long Id) {
		Employee emp = employeeService.findByEmployeeId(Id);
		emp.setUser(user);
		emp.setPassword(pass);
		employeeService.changePassUser(emp);
		if(emp.getRole().equals("admin")) { //meaning employee is an admin
			return "main/Home/home";
		}
		if(emp.getRole().equals("user")) { // meaning employee is an user
			return "main/Home/homeUSer";
		}
		return "main/Home/homeNotlogin";
	}
	
	@GetMapping("/anotation")
	public String anotation(Model model) {
		Employee emp = employeeService.findActiveUser(1);
		String email = emp.getEmail();
		Iterable<Message> messagerec = messageService.findByReceiveEmail(email);
		Iterable<Message> messagesend = messageService.findBySendEmail(email);
		model.addAttribute("messsend", messagesend);
		model.addAttribute("messrec", messagerec);
		
		if(emp.getRole().equals("admin")) {
			return "/main/Anotation/ano-admin";
		}
		return "/main/Anotation/ano-user";
	}
	
	@GetMapping("/newano")
	public String add_newano(Model model) {
		return "main/Anotation/getnew";
	}
	
	@PostMapping("/saveano")
	public String saveano(Model model, @Valid Message messa, Errors errors, @RequestParam("receive") String email, @RequestParam("content") String content) {
		if(errors.hasErrors())
			return "redirect:/emloyees";
		Message mess = new Message();
		mess.setSendEmail(employeeService.findActiveUser(1).getEmail());
		mess.setReceiveEmail(email);
		mess.setContent(content);
		messageService.save(mess);
		return "redirect:/employees";
	}
}
