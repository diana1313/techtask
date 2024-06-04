package com.the.raven.tech.task.service;

import com.the.raven.tech.task.domain.Customer;
import com.the.raven.tech.task.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerDto createCustomer(Customer customer);

    Optional<CustomerDto> getCustomerById(Long id);

    List<CustomerDto> getAllCustomers();

    CustomerDto updateCustomer(Long id, Customer customerDetails);

    void deleteCustomer(Long id);
}
