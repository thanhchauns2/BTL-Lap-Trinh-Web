package com.example.demo.repo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Project;

@Repository
public class ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Project save(Project project) {
        String sql = "INSERT INTO projects (project_id, name, stage, description, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, project.getProjectId(), project.getName(), project.getStage(), project.getDescription(), project.getStartDate(), project.getEndDate());
        return project;
    }

    public List<Project> findAll() {
        String sql = "SELECT * FROM projects";
        List<Project> projects = jdbcTemplate.query(sql, (rs, rowNum) -> new Project(
                rs.getLong("project_id"),
                rs.getString("name"),
                rs.getString("stage"),
                rs.getString("description"),
                rs.getDate("start_date"),
                rs.getDate("end_date")
        ));
        return projects;
    }

    public Project findByProjectId(long id) {
        String sql = "SELECT * FROM projects WHERE project_id = ?";
        Project project = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Project(
                rs.getLong("project_id"),
                rs.getString("name"),
                rs.getString("stage"),
                rs.getString("description"),
                rs.getDate("start_date"),
                rs.getDate("end_date")
        ));
        return project;
    }

    public void delete(Project project) {
        String sql = "DELETE FROM project_employee WHERE project_id = ?";
        jdbcTemplate.update(sql, project.getProjectId());
        sql = "DELETE FROM projects WHERE project_id = ?";
        jdbcTemplate.update(sql, project.getProjectId());
    }

    public void addEmployeeToProject(Project project, Employee employee) {
        String sql = "INSERT INTO project_employee (project_id, employee_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, project.getProjectId(), employee.getEmployeeId());
    }

    public void removeEmployeeFromProject(Project project, Employee employee) {
        String sql = "DELETE FROM project_employee WHERE project_id = ? AND employee_id = ?";
        jdbcTemplate.update(sql, project.getProjectId(), employee.getEmployeeId());
    }

	public Project save(Project project, List<Employee> includingEmployees) {
		// TODO Auto-generated method stub
		save(project);
		List<Employee> oldEmployees = project.getEmployees();
		for (Employee em : oldEmployees) {
			removeEmployeeFromProject(project, em);
		}

		for (Employee em : includingEmployees) {
			addEmployeeToProject(project, em);
		}
		return project;
	}
}
