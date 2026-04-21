package com.employee.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String employeeCode;
    private String company;
    private List<AddressDTO> addressDTOList;
}
