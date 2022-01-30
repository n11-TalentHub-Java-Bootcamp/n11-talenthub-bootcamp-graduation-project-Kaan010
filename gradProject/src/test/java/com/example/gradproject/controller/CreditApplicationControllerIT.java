package com.example.gradproject.controller;

import com.example.gradproject.IntegrationTestSupport;
import com.example.gradproject.dto.CreditApplicationRequest;
import com.example.gradproject.model.CreditApplication;
import com.example.gradproject.model.CreditApplicationId;
import com.example.gradproject.model.CreditResult;
import com.example.gradproject.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreditApplicationControllerIT extends IntegrationTestSupport {

    @Test
    public void testApplyCredit_whenCustomerIdentityExist_shouldReturnApplDto() throws Exception {
        Customer customer = generateCustomer(100);
        Customer customerSaved = customerRepository.save(customer);

        this.mockMvc.perform(post("/v1/credit/apply/" + customerSaved.getIdentityNumber())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.creditApplicationId.customerIdentityNumber", is(customerSaved.getIdentityNumber())));

        List<CreditApplication> allCredits = creditApplicationRepository.findAll();
        assertEquals(1, allCredits.size());


    }

    @Test
    public void testGetCreditApplication_whenCreditExist_shouldReturnCreditApplicationDto() throws Exception {
        CreditApplicationId creditApplicationId = new CreditApplicationId(
                10000000000L,
                LocalDate.of(1999, 12, 30)
        );
        CreditApplicationRequest creditApplicationRequest = new CreditApplicationRequest(
                10000000000L,
                LocalDate.of(1999, 12, 30)
        );
        CreditApplication createdApplication = new CreditApplication(
                new CreditApplicationId(
                        10000000000L,
                        LocalDate.of(1999, 12, 30)
                ),
                CreditResult.DENIED,
                0L,
                "+905430000000"
        );
        creditApplicationRepository.save(createdApplication);

        this.mockMvc.perform(get("/v1/credit/customer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(creditApplicationRequest)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.creditApplicationId.customerIdentityNumber", is(creditApplicationRequest.getCustomerIdentityNumber())));

    }

}