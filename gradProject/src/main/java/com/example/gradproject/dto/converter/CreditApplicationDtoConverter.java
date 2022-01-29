package com.example.gradproject.dto.converter;

import com.example.gradproject.dto.CreditApplicationDto;
import com.example.gradproject.dto.CreditApplicationRequest;
import com.example.gradproject.model.CreditApplication;
import com.example.gradproject.model.CreditApplicationId;

public class CreditApplicationDtoConverter {

    public static CreditApplicationId convertCreditApplicationRequestToCreditApplicationId(CreditApplicationRequest creditApplicationRequest) {
        return new CreditApplicationId(
                creditApplicationRequest.getCustomerIdentityNumber(),
                creditApplicationRequest.getCustomerBirthDate()
        );
    }

    public static CreditApplicationDto convertCreditApplicationToCreditApplicationDto(CreditApplication creditApplication) {
        return new CreditApplicationDto(
                new CreditApplicationId(
                        creditApplication.getCreditApplicationId().getCustomerIdentityNumber(),
                        creditApplication.getCreditApplicationId().getCustomerBirthDate()
                ),
                creditApplication.getCreditResult(),
                creditApplication.getCreditLimit(),
                creditApplication.getCustomerTelephoneNo()
        );
    }

}
