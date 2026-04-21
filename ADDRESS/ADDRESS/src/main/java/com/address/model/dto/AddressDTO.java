package com.address.model.dto;

import com.address.model.enums.AddressType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
