package com.ShipSwift.ShipSwift.controller;
import com.ShipSwift.ShipSwift.DTO.CustomerDto;
import com.ShipSwift.ShipSwift.service.implement.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDto customerDto){
        customerService.updateCustomer(id, customerDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable Long id){
        var customer = customerService.findById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAllCustomers(){
        var customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomerById(@PathVariable Long id){
        customerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
