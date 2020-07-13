package com.monogoaggregate.example.service.impl;

import com.monogoaggregate.example.domain.Customer;
import com.monogoaggregate.example.repository.CustomerMongoRepository;
import com.monogoaggregate.example.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerMongoRepository customerMongoRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        log.info("Received a request to save a customer");
        Customer savedCustomer =  customerMongoRepository.insert(customer);
        log.info("Successfully saved the customer");
        return savedCustomer;
    }
}
