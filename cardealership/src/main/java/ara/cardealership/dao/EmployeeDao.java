/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ara.cardealership.dao;

import ara.cardealership.dto.EmployeeDto;
import java.util.List;

/**
 *
 * @author Rich
 */
public interface EmployeeDao {
    EmployeeDto getEmployeeById(int id);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto addEmployee(EmployeeDto employee);
    void updateEmployee(EmployeeDto employee);
    void deleteEmployeeById (int id);
}
