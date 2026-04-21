package com.address.service;

import com.address.model.dto.AddressDTO;
import com.address.model.dto.AddressRequest;

import java.util.List;

public interface AddressService {
    List<AddressDTO> createAddress(AddressRequest addressRequest);
    List<AddressDTO> updateAddress(AddressRequest addressRequest);
    AddressDTO getSingleAddress(Long addressId);
    List<AddressDTO> getAllAddresses();
    void deleteAddress(Long addressId);
    List<AddressDTO> getAddressByEmployeeId(Long employeeId);
}
