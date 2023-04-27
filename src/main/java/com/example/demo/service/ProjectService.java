package com.example.demo.service;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Project;
import com.example.demo.repo.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }
    public void delete(Project project) {
    	projectRepository.delete(project);
    }

	public Project save(Project project, List<Employee> includingEmployees) {
		// TODO Auto-generated method stub
		return projectRepository.save(project, includingEmployees);
	}
}
