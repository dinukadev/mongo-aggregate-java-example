package com.monogoaggregate.example.service;

import com.monogoaggregate.example.domain.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    List<Customer> getCustomersByFileType(String fileType);
}
