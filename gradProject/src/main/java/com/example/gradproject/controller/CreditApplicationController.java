package com.example.gradproject.controller;


import com.example.gradproject.dto.CreditApplicationDto;
import com.example.gradproject.dto.CreditApplicationRequest;
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

    @PostMapping(value = "/apply/{customerIdentityNo}")
    public ResponseEntity<CreditApplicationDto> createCreditApplicationForCustomer(@PathVariable Long customerIdentityNo) {
        return new ResponseEntity<>(
                creditApplicationService.createCreditApplicationForCustomer(customerIdentityNo),
                HttpStatus.CREATED
        );
    }

}
