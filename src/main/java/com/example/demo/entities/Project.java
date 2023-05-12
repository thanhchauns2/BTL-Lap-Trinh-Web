package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {
	@Id
	private Long projectId;
	
	private String name;
	
	private String stage; // NOTSTARTED, COMPLETED, INPROGRESS
	
	private String description;
	
	@NotBlank(message="date cannot be empty")
	private Date startDate;
	
	@NotBlank(message="date cannot be empty")
	private Date endDate;

	@JsonIgnore
	private List<Employee> employees;
	
	public Project() {
		Random rand = new Random();
		this.projectId = rand.nextLong(2500);
		this.employees = new ArrayList<>();
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Project(String name, String stage, String description, long userId) {
		super();
		Random rand = new Random();
		this.projectId = rand.nextLong(2500);
		this.name = name;
		this.stage = stage;
		this.description = description;
		this.employees = new ArrayList<>();
	}
	
	public Project(long projectId, String name, String stage, String description, Date startDate, Date endDate) {
		this.projectId = projectId;
		this.stage = stage;
		this.description = description;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employees = new ArrayList<>();
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	// convenience method:
	public void addEmployee(Employee emp) {
		if(employees==null) {
			employees = new ArrayList<>();
		}
		employees.add(emp);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}