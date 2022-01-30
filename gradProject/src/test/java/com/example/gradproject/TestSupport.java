package com.example.gradproject;

import com.example.gradproject.model.CreditApplication;
import com.example.gradproject.model.CreditApplicationId;
import com.example.gradproject.model.CreditResult;
import com.example.gradproject.model.Customer;
import com.example.gradproject.util.CreditResultCalculatorUtil;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Optional;

public class TestSupport {
    @NotNull
    public CreditApplication getCreditApplication(Customer customer) {
        return new CreditApplication(
                new CreditApplicationId(
                        customer.getIdentityNumber(),
                        customer.getBirthDate()
                ),
                CreditResultCalculatorUtil.calculateCreditResult(customer.getCreditNote()),
                CreditResultCalculatorUtil.calculateCreditLimit(
                        customer.getCreditNote(),
                        customer.getSalary(),
                        Optional.ofNullable(customer.getAssurance())),
                customer.getTelephone()
        );
    }

    @NotNull
    public String getMessage(Customer customer, CreditApplication creditApplication) {
        return "\nDear " + customer.getName() + " " + customer.getSurName() + ",\n" +
                "Your credit application has been announced. " +
                ((creditApplication.getCreditResult().equals(CreditResult.DENIED) ?
                        ("We are sorry to say that your application has been rejected.") :
                        ("We are happy to say that your application has been approved. Your limit is " + creditApplication.getCreditLimit()))
                                + "Have a good days. sent from n11.com by Kaan Kalan.");
    }

    @NotNull
    public Customer getCustomer() {
        return new Customer(
                "0",
                123,
                "Kaan",
                "Kalan",
                8000,
                "+905050500550",
                LocalDate.of(1996, 10, 2),
                2000,
                300);
    }


}
