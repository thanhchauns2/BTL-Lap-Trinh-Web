package com.example.demo.repo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Employee;

@Repository
public class EmployeeRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Employee save(Employee employee) {
        String sql = "INSERT INTO employee (employee_id, first_name, last_name, email, active, user, password, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
        		employee.getEmail(), employee.getActive(), employee.getUser(), employee.getPassword(), employee.getRole());
        return employee;
    }
    
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Employee(
                        rs.getLong("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getLong("active"),
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getString("role")
                ));
        return employees;
    }
    
    @SuppressWarnings("deprecation")
	public Employee findByEmployeeId(long id) {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Employee(
                        rs.getLong("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getLong("active"),
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getString("role")
                ));
    }

    public void delete(Employee employee) {
        String sql = "DELETE FROM project_employee WHERE employee_id = ?";
        jdbcTemplate.update(sql, employee.getEmployeeId());
        sql = "DELETE FROM employee WHERE employee_id = ?";
        jdbcTemplate.update(sql, employee.getEmployeeId());
    }
    
    public void activeOff(long id) {
    	String sql = "UPDATE employee SET active = 0 WHERE employee_id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void activeOn(long id) {
    	String sql = "UPDATE employee SET active = 1 WHERE employee_id = ?";
        jdbcTemplate.update(sql, id);
    }
    
    @SuppressWarnings("deprecation")
	public Employee findActiveUser(long id) {
        String sql = "SELECT * FROM employee WHERE active = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Employee(
                        rs.getLong("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getLong("active"),
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getString("role")
                ));
    }
    
    public Employee changePassUser(Employee employee) {
        String sql = "UPDATE employee SET user = ? WHERE employee_id = ?";
        jdbcTemplate.update(sql, employee.getUser(), employee.getEmployeeId());
        sql = "UPDATE employee SET password = ? WHERE employee_id = ?";
        jdbcTemplate.update(sql, employee.getPassword(), employee.getEmployeeId());
        return employee;
    }
    
}
