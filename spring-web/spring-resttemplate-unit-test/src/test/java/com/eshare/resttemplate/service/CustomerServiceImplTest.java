package com.eshare.resttemplate.service;

import com.eshare.resttemplate.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CustomerServiceI customerServiceI = new CustomerServiceImpl();


    @Test
    void testGetCustomerList() {
        //given
        Customer customer1 = new Customer(1, "Evan");
        Customer customer2 = new Customer(2, "Alin");
        List<Customer> customerList = new ArrayList<Customer>();
        customerList.add(customer1);
        customerList.add(customer2);
        ParameterizedTypeReference<List<Customer>> responseBodyType = new ParameterizedTypeReference<List<Customer>>(){};
        //When
        Mockito.when(restTemplate.exchange("http://localhost:8080/customers", HttpMethod.GET, null,responseBodyType)).thenReturn(new ResponseEntity(customerList, HttpStatus.OK));

        //expect
        List<Customer> customers = customerServiceI.getCustomerList();

        Assertions.assertEquals(customerList,customers);
    }


}