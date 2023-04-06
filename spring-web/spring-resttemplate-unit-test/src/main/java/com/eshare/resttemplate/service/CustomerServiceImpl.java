package com.eshare.resttemplate.service;

import com.eshare.resttemplate.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerServiceI {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Customer> getCustomerList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ParameterizedTypeReference<List<Customer>> responseBodyType = new ParameterizedTypeReference<List<Customer>>(){};
        return restTemplate.exchange("http://localhost:8080/customers", HttpMethod.GET, null,responseBodyType).getBody();
    }

}
