package com.employee.service;

import com.employee.model.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
    EmployeeDTO getEmployeeById(Long id);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeByEmpCodeAndCompany(String employeeCode, String company);
}
