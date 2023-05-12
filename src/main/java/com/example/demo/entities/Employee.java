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
	
	private long active;
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@NotBlank(message="*Must give a user name")
	@Size(min=2, max=50)
	private String user;
	
	@NotBlank(message="*Must give a password")
	@Size(min=2, max=50)
	private String password;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getActive() {
		return active;
	}

	public void setActive(long active) {
		this.active = active;
	}

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
	
	public Employee(long id, String fn, String ln, String e, long active, String user, String password, String role) {
		this.employeeId = id;
		this.firstName = fn;
		this.lastName = ln;
		this.email = e;
		this.active = active;
		this.user = user;
		this.password = password;
		this.role = role;
	}
}
