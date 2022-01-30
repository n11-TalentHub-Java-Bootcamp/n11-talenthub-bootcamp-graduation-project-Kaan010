package com.example.gradproject.controller;


import com.example.gradproject.dto.CreditApplicationDto;
import com.example.gradproject.dto.CreditApplicationRequest;
import com.example.gradproject.model.CreditApplication;
import com.example.gradproject.service.CreditApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/credit")
public class CreditApplicationController {

    private final CreditApplicationService creditApplicationService;

    public CreditApplicationController(CreditApplicationService creditApplicationService) {
        this.creditApplicationService = creditApplicationService;
    }


    @GetMapping
    public ResponseEntity<List<CreditApplicationDto>> getAllCreditApplications() {
        return new ResponseEntity<>(
                creditApplicationService.getAllCreditApplications(),
                HttpStatus.OK
        );
    }


    @GetMapping(value = "/customer")
    public ResponseEntity<CreditApplicationDto> getCreditApplicationsById(@Valid @RequestBody CreditApplicationRequest creditApplicationRequest) {
        return new ResponseEntity<>(
                creditApplicationService.getCreditByIdentityNumberAndBirthDate(creditApplicationRequest),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/apply/{customerId}")
    public ResponseEntity<CreditApplicationDto> createCreditApplicationForCustomer(@PathVariable String customerId) {
        return new ResponseEntity<>(
                creditApplicationService.createCreditApplicationForCustomer(customerId),
                HttpStatus.CREATED
        );
    }

}
