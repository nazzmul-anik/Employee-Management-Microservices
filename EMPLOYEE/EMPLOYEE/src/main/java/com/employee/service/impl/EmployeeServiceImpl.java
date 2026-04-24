package com.employee.service.impl;

import com.commonlib.exception.BadRequestException;
import com.commonlib.exception.CustomException;
import com.commonlib.exception.MissingParameterException;
import com.commonlib.exception.ResourceNotFoundException;
import com.employee.client.AddressClient;
import com.employee.model.dto.AddressDTO;
import com.employee.model.dto.EmployeeDTO;
import com.employee.model.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository  employeeRepository;
    private final ModelMapper modelMapper;
    private final AddressClient addressClient;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        if(employeeDTO.getId() != null){
            throw new RuntimeException("Employee already exists.");
        }
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee.setCreatedAt(LocalDateTime.now());
        employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        if(id == null || employeeDTO.getId() == null){
            throw new BadRequestException("Employee id or employee id is null.");
        }
        if(!Objects.equals(id, employeeDTO.getId())){
            throw new BadRequestException("Employee id does not match.");
        }
        employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+id));

        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee.setUpdatedAt(LocalDateTime.now());
        employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public void deleteEmployee(Long id) {
       Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+id));
       employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+ id));
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        List<AddressDTO> addressDTOList = new ArrayList<>();
        try{
            addressDTOList = addressClient.getAddressByEmployeeId(id);
            employeeDTO.setAddressDTOList(addressDTOList);
        }catch (ResourceNotFoundException e){
            log.error("Employee not found with id: {}", id);
        }

        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeDTOList = employeeRepository.findAll().stream()
                .map(employee->{
                    return modelMapper.map(employee, EmployeeDTO.class);
                }).toList();
        if(employeeDTOList.isEmpty()){
            throw new ResourceNotFoundException("No employee found.");
        }
        for(EmployeeDTO employeeDTO : employeeDTOList){

            try{
                List<AddressDTO> addressDTOList =
                        addressClient.getAddressByEmployeeId(employeeDTO.getId());
                employeeDTO.setAddressDTOList(addressDTOList);
            }catch (CustomException e){
                log.warn("Could not fetch addresses for employeeId: {} | {}", employeeDTO.getId(), e.getMessage());
            }
        }
        return  employeeDTOList;
    }

    @Override
    public EmployeeDTO getEmployeeByEmpCodeAndCompany(String employeeCode, String company) {
        List<String> missingParameters = new ArrayList<>();
        if(employeeCode == null || employeeCode.trim().isEmpty()){
            missingParameters.add("employeeCode");
        }
        if(company == null || company.trim().isEmpty()){
            missingParameters.add("company");
        }
        if(!missingParameters.isEmpty()){
            String message = missingParameters.stream().collect(Collectors.joining(","));
            throw new MissingParameterException("Please provide " + message);
        }
        Employee employee = employeeRepository.findByEmployeeCodeAndCompany(employeeCode, company)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found with employeeCode : "+employeeCode + " and company : "+company));
        return modelMapper.map(employee, EmployeeDTO.class);
    }
}
