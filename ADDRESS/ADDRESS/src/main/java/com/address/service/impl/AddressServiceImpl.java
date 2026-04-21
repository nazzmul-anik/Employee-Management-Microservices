package com.address.service.impl;

import com.address.client.EmployeeClient;
import com.address.exception.ResourceNotFoundException;
import com.address.model.dto.AddressDTO;
import com.address.model.dto.AddressRequest;
import com.address.model.dto.EmployeeDTO;
import com.address.model.entity.Address;
import com.address.repository.AddressRepository;
import com.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final EmployeeClient employeeClient;

    @Override
    public List<AddressDTO> createAddress(AddressRequest addressRequest) {
        // TODO: check if employee is exists
        employeeClient.getEmployeeById(addressRequest.getEmployeeId());

        List<Address> listOfAddress = saveOrUpdateAddressRequest(addressRequest);
        List<Address> saveListOfAddress = addressRepository.saveAll(listOfAddress);

        return saveListOfAddress.stream().map(address -> {
            return modelMapper.map(address, AddressDTO.class);
        }).toList();
    }

    @Override
    public List<AddressDTO> updateAddress(AddressRequest addressRequest) {
        // TODO: check if employee is exists
        employeeClient.getEmployeeById(addressRequest.getEmployeeId());

        List<Address> addressByEmployeeIdList = addressRepository.findAllByEmployeeId(addressRequest.getEmployeeId());
        if(addressByEmployeeIdList.isEmpty()){
            log.info("No address found for employee id {}", addressRequest.getEmployeeId());
            log.info("Creating new address for  employee id {}", addressRequest.getEmployeeId());
        }
        List<Address> updateAddressList = saveOrUpdateAddressRequest(addressRequest);
        List<Long> upcomingNonNullIds = updateAddressList.stream()
                .map(Address::getId)
                .filter(Objects::nonNull)
                .toList();
        List<Long> existingIds = addressByEmployeeIdList.stream()
                .map(Address::getId)
                .toList();
        List<Long> idsToDelete = existingIds.stream().filter(id -> !upcomingNonNullIds.contains(id)).toList();
        if(!idsToDelete.isEmpty()){
            addressRepository.deleteAllById(idsToDelete);
        }
        List<Address> updatedAddress = addressRepository.saveAll(updateAddressList);
        return updatedAddress.stream().map(address-> modelMapper.map(address, AddressDTO.class)).toList();
    }

    @Override
    public AddressDTO getSingleAddress(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(()->new ResourceNotFoundException("Address not found with id " + addressId));
        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<Address> listOfAddress = addressRepository.findAll();
        if(listOfAddress.isEmpty()){
            throw new ResourceNotFoundException("No address found");
        }
        return listOfAddress.stream().map(address-> modelMapper.map(address, AddressDTO.class)).toList();
    }

    @Override
    public void deleteAddress(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(()->new ResourceNotFoundException("Address not found with id " + addressId));
        addressRepository.delete(address);
    }

    @Override
    public List<AddressDTO> getAddressByEmployeeId(Long employeeId) {

        List<Address> addressList = addressRepository.findAllByEmployeeId(employeeId);
        if(addressList.isEmpty()){
            log.info("No address found for employee id {}", employeeId);
            return List.of();
        }
        return addressList.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class)).toList();
    }

    private List<Address> saveOrUpdateAddressRequest(AddressRequest addressRequest) {
        return addressRequest.getAddressRequestDTOList().stream()
                .map(addressRequestDTO -> {
                    Address address = modelMapper.map(addressRequestDTO, Address.class);
                    address.setEmployeeId(addressRequest.getEmployeeId());
                    return address;
                }).toList();
    }
}
