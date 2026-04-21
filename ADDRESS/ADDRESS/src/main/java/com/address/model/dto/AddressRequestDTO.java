package com.address.model.dto;

import com.address.model.enums.AddressType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {
    private Long id;
    private String street;
    private String zipCode;
    private String city;
    private String country;
    private AddressType addressType;
}
