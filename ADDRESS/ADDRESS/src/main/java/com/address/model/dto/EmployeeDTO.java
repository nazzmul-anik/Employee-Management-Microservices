package com.address.model.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String employeeCode;
    private String company;
}
