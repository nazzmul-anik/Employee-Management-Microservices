package com.address.controller;

import com.address.model.dto.AddressDTO;
import com.address.model.dto.AddressRequest;
import com.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<List<AddressDTO>> createAddress(@RequestBody AddressRequest addressRequest) {
        List<AddressDTO> addressDTOList = addressService.createAddress(addressRequest);
        return new ResponseEntity<>(addressDTOList, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<List<AddressDTO>> updateAddress(@RequestBody AddressRequest addressRequest) {
        List<AddressDTO> addressDTOList = addressService.updateAddress(addressRequest);
        return new ResponseEntity<>(addressDTOList, HttpStatus.OK);
    }

    @GetMapping("/all-address")
    public ResponseEntity<List<AddressDTO>> getAllAddresses(){
        List<AddressDTO> addressDTOList = addressService.getAllAddresses();
        return new ResponseEntity<>(addressDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable("id") Long addressId){
        AddressDTO addressDTO = addressService.getSingleAddress(addressId);
        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") Long addressId){
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>("Address "+ addressId+ " : deleted successfully!!", HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<AddressDTO>> getAddressByEmployeeId(@PathVariable Long id){
        List<AddressDTO> addressDTOList = addressService.getAddressByEmployeeId(id);
        return new ResponseEntity<>(addressDTOList, HttpStatus.OK);
    }
}
