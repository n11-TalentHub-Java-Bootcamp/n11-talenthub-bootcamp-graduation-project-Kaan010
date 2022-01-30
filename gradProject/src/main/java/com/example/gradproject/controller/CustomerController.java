package com.example.gradproject.controller;


import com.example.gradproject.dto.CustomerRequest;
import com.example.gradproject.dto.CustomerDto;
import com.example.gradproject.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//  GET  localhost:8080/v1/customer
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return new ResponseEntity<>(
                customerService.getAllCustomers(),
                HttpStatus.OK
        );
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> getAllCustomers(@PathVariable String id) {
        return new ResponseEntity<>(
                customerService.getCustomerById(id),
                HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<CustomerDto> createCustomer(@Valid  @RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<>(customerService.createCustomer(customerRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id,@Valid @RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<>(
                customerService.updateCustomerById(id, customerRequest),
                HttpStatus.OK
        );
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok().build();
    }




}
