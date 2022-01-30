package com.example.gradproject;

import com.example.gradproject.model.Customer;
import com.example.gradproject.repository.CreditApplicationRepository;
import com.example.gradproject.repository.CustomerRepository;
import com.example.gradproject.service.CreditApplicationService;
import com.example.gradproject.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Context ayaga kaldirir
@TestPropertySource(locations = "classpath:application-test.properties")
// Test context icin kullanilacak propertyleri ayarlar.
//classpath herzaman bizim resource file'ımızı gösterir
@DirtiesContext
@AutoConfigureMockMvc // Context icerisindeki servletleri ayaga kaldirir.
//fake tomcat kaldırıp url'leri dinleyecek hale getiriyor
public class IntegrationTestSupport {

    public final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    public CustomerService customerService;
    @Autowired
    public CustomerRepository customerRepository;
    @Autowired
    public CreditApplicationService creditApplicationService;
    @Autowired
    public CreditApplicationRepository creditApplicationRepository;
    @Autowired
    public MockMvc mockMvc; //endpointlere istek yapmamızı sağlayan özel bir sınıf olarak düşün

    @BeforeEach
    public void setup() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
    }

    public List<Customer> generateCustomers(int size) {
        return IntStream.range(0, size)
                .mapToObj(this::generateCustomer)
                .collect(Collectors.toList());
    }

    public Customer generateCustomer(int i) {
        return new Customer(
                "asd",
                10000000000L + i,
                "customer-name-" + i,
                "customer-surname-" + i,
                10000 + 100 * i,
                "+9055555555" + i,
                LocalDate.of(1999, 12, 30),
                null,
                200);
    }


}
