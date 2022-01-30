package com.example.gradproject.service;

import com.example.gradproject.TestSupport;
import com.example.gradproject.exception.CreditApplicationCRUDException;
import com.example.gradproject.model.CreditApplication;
import com.example.gradproject.model.Customer;
import com.example.gradproject.repository.CreditApplicationRepository;
import com.example.gradproject.smsConfig.SmsRequest;
import com.example.gradproject.smsConfig.SmsService;
import com.twilio.exception.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class CreditApplicationServiceTest extends TestSupport {

    private SmsService smsService;
    private CustomerService customerService;
    private CreditApplicationRepository creditApplicationRepository;

    private CreditApplicationService creditApplicationService;

    @BeforeEach
    void setUp() {
        smsService = Mockito.mock(SmsService.class);
        customerService = Mockito.mock(CustomerService.class);
        creditApplicationRepository = Mockito.mock(CreditApplicationRepository.class);
        creditApplicationService = new CreditApplicationService(smsService, customerService, creditApplicationRepository);
    }

    @Test
    void testCreateCreditApplicationForCustomer_whenCustomerNotExist_ShouldThrowCreditApplicationCRUDException() {
        String customerId = "0";

        Mockito.when(customerService.getCustomer(customerId)).thenReturn(Optional.empty());

        assertThrows(CreditApplicationCRUDException.class,
                () -> creditApplicationService.createCreditApplicationForCustomer(customerId));

        Mockito.verify(customerService).getCustomer(anyString());
        Mockito.verifyNoInteractions(smsService);
        Mockito.verifyNoInteractions(creditApplicationRepository);
    }

    @Test
    void testCreateCreditApplicationForCustomer_whenCustomerExistButPhoneNumberNotVerified_LogMessageMustBePrinted() {

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        Customer customer = getCustomer();
        CreditApplication creditApplication = getCreditApplication(customer);

        Mockito.when(customerService.getCustomer(anyString())).thenReturn(Optional.of(customer));
        Mockito.when(creditApplicationRepository.save(creditApplication)).thenReturn(creditApplication);
        Mockito.when(smsService.sendSms(any(SmsRequest.class))).thenThrow(ApiException.class);

        creditApplicationService.createCreditApplicationForCustomer(anyString());
        String expectedLog = "Message could not sent because telephone number not validated by admin. Pls contact with Kaan Kalan\n" +
                "Your message is:";
        assertEquals(expectedLog, errContent.toString());

        Mockito.verify(customerService).getCustomer(anyString());
        Mockito.verify(creditApplicationRepository).save(any(CreditApplication.class));
        Mockito.verify(smsService).sendSms(any(SmsRequest.class));
    }

    @Test
    void testCreateCreditApplicationForCustomer_whenCustomerExistAndPhoneNumberVerified_LogMessageMustNotBePrinted() {

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Customer customer = getCustomer();
        CreditApplication creditApplication = getCreditApplication(customer);

        Mockito.when(customerService.getCustomer(anyString())).thenReturn(Optional.of(customer));
        Mockito.when(creditApplicationRepository.save(creditApplication)).thenReturn(creditApplication);
        Mockito.when(smsService.sendSms(any(SmsRequest.class))).thenReturn(true);

        creditApplicationService.createCreditApplicationForCustomer(anyString());
        String expectedErrLog = "";
        assertEquals(expectedErrLog, errContent.toString());
        String expectedOutLog = "SMS Notification has been sent. Have a nice days. n11.com\n";
        assertEquals(expectedOutLog, outContent.toString());

        Mockito.verify(customerService).getCustomer(anyString());
        Mockito.verify(creditApplicationRepository).save(any(CreditApplication.class));
        Mockito.verify(smsService).sendSms(any(SmsRequest.class));
    }
}