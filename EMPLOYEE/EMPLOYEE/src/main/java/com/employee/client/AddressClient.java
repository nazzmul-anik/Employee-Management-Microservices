package com.employee.client;

import com.employee.model.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "addressClient", url = "${address.service.url}")
@FeignClient(name = "ADDRESS")
public interface AddressClient {

    @GetMapping("/api/addresses/employee/{id}")
    List<AddressDTO> getAddressByEmployeeId(@PathVariable Long id);
}
