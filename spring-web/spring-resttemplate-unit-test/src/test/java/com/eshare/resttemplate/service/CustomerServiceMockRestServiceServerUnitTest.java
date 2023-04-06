package com.eshare.resttemplate.service;

import com.eshare.resttemplate.SpringTestConfig;
import com.eshare.resttemplate.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringTestConfig.class)
public class CustomerServiceMockRestServiceServerUnitTest {

    @Autowired
    private CustomerServiceI customerServiceI;

    @Autowired
    private RestTemplate restTemplate;

    @Value("classpath:mockdata/response.json")
    Resource mockResponse;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void givenMockingIsDoneByMockCustomerRestServiceServer_whenGetIsCalled_thenReturnsMockedCustomerListObject() throws Exception {
        //given
        Customer customer1 = new Customer(1, "Evan");
        Customer customer2 = new Customer(2, null);
        //Customer customer2 = new Customer(2, "Alin");
        List<Customer> customerList = new ArrayList<Customer>();
        customerList.add(customer1);
        customerList.add(customer2);
        //when
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:8080/customers")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body( FileUtils.readFileToString(mockResponse.getFile(), Charset.forName("utf-8")))
                );

        List<Customer> customers = customerServiceI.getCustomerList();
        mockServer.verify();
        //expect
        Assertions.assertEquals(customerList, customers);

    }
}