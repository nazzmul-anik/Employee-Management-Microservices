package com.address.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    private Long employeeId;
    private List<AddressRequestDTO> addressRequestDTOList;
}
