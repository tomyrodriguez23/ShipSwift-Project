package com.ShipSwift.ShipSwift.service.interfaces;

import com.ShipSwift.ShipSwift.DTO.CustomerDto;

import java.util.List;

public interface CustomerServiceInterface {

    CustomerDto findById(Long id);
    List<CustomerDto> findAll();
    void deleteById(Long id);
    void updateCustomer(Long id, CustomerDto customerDto);

}
