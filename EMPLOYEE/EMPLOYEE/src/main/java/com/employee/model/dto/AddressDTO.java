package com.employee.model.dto;


import com.employee.model.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private Long employeeId;
    private String street;
    private String zipCode;
    private String city;
    private String country;
    private AddressType addressType;
}
