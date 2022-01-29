package com.example.gradproject.service;

import com.example.gradproject.smsConfig.SmsRequest;
import com.example.gradproject.smsConfig.SmsService;
import com.example.gradproject.dto.CreditApplicationDto;
import com.example.gradproject.dto.CreditApplicationRequest;
import com.example.gradproject.dto.converter.CreditApplicationDtoConverter;
import com.example.gradproject.exception.CreditApplicationCRUDException;
import com.example.gradproject.model.CreditApplication;
import com.example.gradproject.model.CreditApplicationId;
import com.example.gradproject.model.CreditResult;
import com.example.gradproject.model.Customer;
import com.example.gradproject.repository.CreditApplicationRepository;
import com.example.gradproject.util.CreditResultCalculatorUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditApplicationService {

    private final SmsService smsService;
    private final CustomerService customerService;
    private final CreditApplicationRepository creditApplicationRepository;

    public CreditApplicationService(SmsService smsService, CustomerService customerService, CreditApplicationRepository creditApplicationRepository) {
        this.smsService = smsService;
        this.customerService = customerService;
        this.creditApplicationRepository = creditApplicationRepository;
    }

    private void sendSMS(SmsRequest smsRequest) {
        smsService.sendSms(smsRequest);
    }

    public CreditApplicationDto createCreditApplicationForCustomer(String customerId) {
        Optional<Customer> applicant = customerService.getCustomer(customerId);
        if (applicant.isPresent()) {
            Customer customer = applicant.get();
            CreditApplication creditApplication = getNewCreditApplication(customer);
            creditApplicationRepository.save(creditApplication);
            sendSMS(
                    new SmsRequest(
                            creditApplication.getCustomerTelephoneNo(),
                            "\nDear "+customer.getName()+" "+customer.getSurName()+",\n"+
                            "Your credit application has been announced. " +
                                    (creditApplication.getCreditResult().equals(CreditResult.DENIED) ?
                                            ("We are sorry to say that your application has been rejected.") :
                                            ("We are happy to say that your application has been approved. Your limit is " + creditApplication.getCreditLimit())
                                                    + "Have a good days. sent from n11.com by Kaan Kalan.")
                    ));
            return CreditApplicationDtoConverter.convertCreditApplicationToCreditApplicationDto(creditApplication);
        } else throw new CreditApplicationCRUDException("Credit Application could not created");
    }

    @NotNull
    private CreditApplication getNewCreditApplication(Customer customer) {
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


    public List<CreditApplicationDto> getAllCreditApplications() {
        return creditApplicationRepository.findAll().stream()
                .map(CreditApplicationDtoConverter::convertCreditApplicationToCreditApplicationDto)
                .collect(Collectors.toList());
    }

    public CreditApplicationDto getCreditByIdentityNumberAndBirthDate(CreditApplicationRequest creditApplicationRequest) {
        return creditApplicationRepository.findById(
                        CreditApplicationDtoConverter.convertCreditApplicationRequestToCreditApplicationId(creditApplicationRequest)
                )
                .map(CreditApplicationDtoConverter::convertCreditApplicationToCreditApplicationDto)
                .orElseThrow(() -> new CreditApplicationCRUDException("There is no credit application with this id"));
    }
}