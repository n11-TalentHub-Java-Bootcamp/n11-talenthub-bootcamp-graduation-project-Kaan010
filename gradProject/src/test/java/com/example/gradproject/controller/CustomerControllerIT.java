package com.example.gradproject.controller;

import com.example.gradproject.IntegrationTestSupport;
import com.example.gradproject.dto.CustomerRequest;
import com.example.gradproject.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerIT extends IntegrationTestSupport {

    @Test
    public void testGetCustomerByIdentityNumber_whenCustomerIdExist_shouldReturnCustomerDto() throws Exception {
        Customer customer = generateCustomer(100);
        Customer customerSaved = customerRepository.save(customer);
        this.mockMvc.perform(get("/v1/customer/filter/" + customerSaved.getIdentityNumber())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(customerSaved.getId())));

        customerRepository.deleteAll();
    }

    @Test
    public void testGetCustomerByIdentityNumber_whenCustomerIdExist_shouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/customer/filter/" + "not-exist-identity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        customerRepository.deleteAll();
    }

    @Test
    public void testCreateCustomer_whenCustomerRequestIsValid_shouldCreateCustomerAndReturnCustomerDto() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest(
                18800000000L,
                "testCustomerN",
                "testCustomerS",
                3000,
                "05051111111",
                LocalDate.of(1999, 12, 30),
                null
        );
        this.mockMvc.perform(post("/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(customerRequest)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is("testCustomerN")))
                .andExpect(jsonPath("$.surName", is("testCustomerS")))
                .andExpect(jsonPath("$.salary", is(customerRequest.getSalary()))) //array Object
                .andExpect(jsonPath("$.telephone", is(customerRequest.getTelephone())))
                .andExpect(jsonPath("$.birthDate", is("1999-12-30")));

        List<Customer> createdCustomer = customerRepository.findAll();
        assertEquals(1, createdCustomer.size());

        customerRepository.deleteAll();
    }

    @Test
    public void testCreateCustomer_whenCustomerRequestIsNotValid_shouldNotCreateCustomerAndReturn400BadRequest() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest(
                0,
                "",
                "",
                0,
                "",
                LocalDate.of(1999, 12, 30),
                null
        );
        this.mockMvc.perform(post("/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(customerRequest)))
                .andExpect(status().isBadRequest());

        List<Customer> createdCustomer = customerRepository.findAll();
        assertEquals(0, createdCustomer.size());

        customerRepository.deleteAll();
    }

    @Test
    public void testUpdateCustomer_whenCustomerRequestIsValid_shouldUpdateCustomerAndReturnCustomerDto() throws Exception {
        Customer initialCustomer = new Customer(
                18800000000L,
                "testCustomerN",
                "testCustomerS",
                3000,
                "05051111111",
                LocalDate.of(1999, 12, 30),
                null,
                null
        );
        customerRepository.save(initialCustomer);
        CustomerRequest updateCustomer = new CustomerRequest(
                initialCustomer.getIdentityNumber(),
                "testCustomerN",
                "testCustomerS",
                5555,
                "05051111111",
                LocalDate.of(1999, 12, 30),
                2000
        );
        this.mockMvc.perform(put("/v1/customer/"+updateCustomer.getIdentityNumber())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(updateCustomer)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is(updateCustomer.getName())))
                .andExpect(jsonPath("$.surName", is(updateCustomer.getSurName())))
                .andExpect(jsonPath("$.salary", is(updateCustomer.getSalary())))
                .andExpect(jsonPath("$.telephone", is(updateCustomer.getTelephone())));
        List<Customer> createdCustomer = customerRepository.findAll();
        assertEquals(1, createdCustomer.size());

        customerRepository.deleteAll();

    }
}