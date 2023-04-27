package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Employee {
	@Id
	private long employeeId;
	
	@NotBlank(message="*Must give a first name")
	@Size(min=2, max=50)
	private String firstName;
	
	@NotBlank(message="*Must give a last name")
	@Size(min=1, max=50)
	private String lastName;
	
	@NotBlank
	@Email(message="*Must be a valid email address")
	private String email;
	
	public Employee() {
			
	}

	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Employee(long id, String fn, String ln, String e) {
		this.employeeId = id;
		this.firstName = fn;
		this.lastName = ln;
		this.email = e;
	}
}
