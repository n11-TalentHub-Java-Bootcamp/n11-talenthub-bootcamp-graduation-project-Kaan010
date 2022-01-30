package com.example.gradproject.controller;

import com.example.gradproject.IntegrationTestSupport;
import com.example.gradproject.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


    }

    @Test
    public void testGetCustomerByIdentityNumber_whenCustomerIdExist_shouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/v1/customer/filter/" + "not-exist-identity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isExpectationFailed());
    }
}