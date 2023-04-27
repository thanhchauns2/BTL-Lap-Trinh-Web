//package com.example.demo.repo;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.example.demo.entities.Employee;
//
//@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//
//}

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
        String sql = "INSERT INTO employee (employee_id, first_name, last_name, email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
        return employee;
    }
    
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Employee(
                        rs.getLong("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
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
                        rs.getString("email")
                ));
    }

    public void delete(Employee employee) {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        jdbcTemplate.update(sql, employee.getEmployeeId());
    }

}
