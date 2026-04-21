package com.employee.controller;

import com.employee.exception.MissingParameterException;
import com.employee.model.dto.EmployeeDTO;
import com.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id,
                                            @RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee Deleted Successfully.", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        List<EmployeeDTO> employeeDTOs = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }

    @GetMapping("/get-by-employeeCode-and-company")
    public ResponseEntity<?> getEmployeeByEmpCodeAncCompany(@RequestParam(required = false) String employeeCode,
                                                            @RequestParam(required = false) String company){
        EmployeeDTO employeeDTO = employeeService.getEmployeeByEmpCodeAndCompany(employeeCode, company);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }
}
