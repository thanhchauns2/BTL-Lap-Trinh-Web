package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Employee;
import com.example.demo.repo.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }
    
    public Employee findByEmployeeId(long id) {
    	return employeeRepository.findByEmployeeId(id);
    }
    
    public void activeOff(long id) {
        employeeRepository.activeOff(id);
    }
    
    public void activeOn(long id) {
        employeeRepository.activeOn(id);
    }
    
    public Employee findActiveUser(long id) {
    	return employeeRepository.findActiveUser(id);
    }
    
    public Employee changePassUser(Employee employee) {
    	return employeeRepository.changePassUser(employee);
    }
}
