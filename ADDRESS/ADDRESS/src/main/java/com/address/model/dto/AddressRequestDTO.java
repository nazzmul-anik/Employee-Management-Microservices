package com.address.model.dto;

import com.address.model.enums.AddressType;

import com.commonlib.entity.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO extends AuditableEntity {
    private Long id;
    private String street;
    private String zipCode;
    private String city;
    private String country;
    private AddressType addressType;
    private LocalDateTime  createdAt;
    private LocalDateTime updatedAt;
}
