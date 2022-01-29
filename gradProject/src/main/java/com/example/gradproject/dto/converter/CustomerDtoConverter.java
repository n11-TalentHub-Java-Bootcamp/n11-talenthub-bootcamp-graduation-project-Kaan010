package com.example.gradproject.dto.converter;

import com.example.gradproject.dto.CustomerRequest;
import com.example.gradproject.dto.CustomerDto;
import com.example.gradproject.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerDtoConverter {
    public CustomerDto convertCustomerToCustomerDto(Customer from){//TO SEND FRONTEND
        return new CustomerDto(
                Objects.requireNonNull(from.getId()),
                from.getIdentityNumber(),
                from.getName(),
                from.getSurName(),
                from.getSalary(),
                from.getTelephone(),
                from.getBirthDate(),
                from.getAssurance(),
                from.getCreditNote()
        );
    }

    public Customer convertCustomerDtoToCustomer(CustomerDto from){//TO GET FRONTEND
        return new Customer(
                from.getId(),
                from.getIdentityNumber(),
                from.getName(),
                from.getSurName(),
                from.getSalary(),
                from.getTelephone(),
                from.getBirthDate(),
                from.getAssurance(),
                from.getCreditNote()
        );
    }

    public Customer convertCreateCustomerRequestToCustomer(CustomerRequest from){//TO GET FRONTEND
        return new Customer(
                from.getIdentityNumber(),
                from.getName(),
                from.getSurName(),
                from.getSalary(),
                from.getTelephone(),
                from.getBirthDate(),
                from.getAssurance(),
                null
        );
    }


}
