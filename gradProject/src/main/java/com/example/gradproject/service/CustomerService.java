package com.example.gradproject.service;

import com.example.gradproject.dto.CustomerRequest;
import com.example.gradproject.dto.CustomerDto;
import com.example.gradproject.dto.converter.CustomerDtoConverter;
import com.example.gradproject.exception.CustomerCRUDException;
import com.example.gradproject.model.Customer;
import com.example.gradproject.repository.CustomerRepository;
import com.example.gradproject.util.CreditNoteUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerDtoConverter::convertCustomerToCustomerDto)
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerByIdentity(Long identity){
        return customerRepository.findCustomerByIdentityNumber(identity)
                .map(customerDtoConverter::convertCustomerToCustomerDto)
                .orElseThrow(()-> new CustomerCRUDException("Customer could not found with identity " + identity));
    }

    public CustomerDto getCustomerById(String id) {
        return customerRepository.findById(id)
                .map(customerDtoConverter::convertCustomerToCustomerDto)
                .orElseThrow(() -> new CustomerCRUDException("Customer could not found with id " + id));
    }

    public CustomerDto createCustomer(CustomerRequest customerRequest) {
        return Optional.of(customerRequest)
                .map(customerDtoConverter::convertCreateCustomerRequestToCustomer)
                .map(customer -> {
                    customer.setCreditNote(CreditNoteUtil.calculte(customer.getIdentityNumber()));
                    return customer;
                })
                .map(customerRepository::save)
                .map(customerDtoConverter::convertCustomerToCustomerDto)
                .orElseThrow(() -> new CustomerCRUDException("Customer could not created"));


    }

    public CustomerDto updateCustomerByIdentity(Long identity, CustomerRequest customerDto) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByIdentityNumber(identity);
        customerOptional.ifPresent(customer -> {
                    customer = new Customer(
                            customer.getId(),
                            customerDto.getIdentityNumber(),
                            customerDto.getName(),
                            customerDto.getSurName(),
                            customerDto.getSalary(),
                            customerDto.getTelephone(),
                            customerDto.getBirthDate(),
                            customerDto.getAssurance(),
                            null
                    );
                    customer.setCreditNote(CreditNoteUtil.calculte(customer.getIdentityNumber()));
                    customerRepository.save(customer);
                }
        );
        return customerOptional.map(customerDtoConverter::convertCustomerToCustomerDto)
                .orElseThrow(() -> new CustomerCRUDException("Customer could not created"));
    }

    public void deleteCustomerByIdentity(Long identity) {
        customerRepository.deleteCustomerByIdentityNumber(identity);
    }

    //used by another service
    protected Optional<Customer> getCustomer(Long identity) {
        return customerRepository.findCustomerByIdentityNumber(identity);
    }

}
