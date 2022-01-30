package com.example.gradproject.repository;

import com.example.gradproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findCustomerByIdentityNumber(Long identityNumber);

    void deleteCustomerByIdentityNumber(Long identityNumber);

    void deleteAll();
}
