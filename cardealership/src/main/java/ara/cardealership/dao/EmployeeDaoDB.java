/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ara.cardealership.dao;

import ara.cardealership.dto.EmployeeDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rich
 */

@Repository
public class EmployeeDaoDB implements EmployeeDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public EmployeeDto getEmployeeById(int id) {
        try {
            final String GET_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id = ?";
            return jdbc.queryForObject(GET_EMPLOYEE_BY_ID, new EmployeeMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        final String GET_ALL_EMPLOYEES = "SELECT * FROM employee";
        return jdbc.query(GET_ALL_EMPLOYEES, new EmployeeMapper());
    }

    @Override
    @Transactional
    public EmployeeDto addEmployee(EmployeeDto employee) {
        final String INSERT_EMPLOYEE = "INSERT INTO employee(firstname, lastname, email,"
                + "privilege, password) VALUES (?, ?, ?, ?, ?)";
        jdbc.update(INSERT_EMPLOYEE,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPrivilege(),
                employee.getPassword());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        employee.setEmployeeId(newId);
        return employee;
    }

    @Override
    public void updateEmployee(EmployeeDto employee) {
        final String UPDATE_EMPLOYEE = "UPDATE employee SET firstname = ?, lastname = ?, "
                + "email = ?, privilege = ?, password = ? WHERE id = ?";
        jdbc.update(UPDATE_EMPLOYEE,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPrivilege(),
                employee.getPassword(),
                employee.getEmployeeId());
    }

    @Override
    public void deleteEmployeeById(int id) {
                final String UPDATE_EMPLOYEE = "UPDATE employee SET active = 0 WHERE id = ?";
        jdbc.update(UPDATE_EMPLOYEE,id);
    }

    public static final class EmployeeMapper implements RowMapper<EmployeeDto> {

        @Override
        public EmployeeDto mapRow(ResultSet rs, int index) throws SQLException {
            EmployeeDto employee = new EmployeeDto();
            employee.setEmployeeId(rs.getInt("id"));
            employee.setFirstName(rs.getString("firstName"));
            employee.setLastName(rs.getString("lastName"));
            employee.setEmail(rs.getString("email"));
            employee.setPrivilege(rs.getString("privilege"));
            employee.setIsActive(rs.getBoolean("active"));
            return employee;
        }
    }
}
