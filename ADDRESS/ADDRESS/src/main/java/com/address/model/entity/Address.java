package com.address.model.entity;

import com.address.model.enums.AddressType;
import com.commonlib.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private String street;
    private String zipCode;
    private String city;
    private String country;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
}
