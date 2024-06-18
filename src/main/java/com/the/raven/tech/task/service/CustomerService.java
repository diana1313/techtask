package com.the.raven.tech.task.service;

import com.the.raven.tech.task.dto.CreateCustomerDto;
import com.the.raven.tech.task.dto.CustomerResponseDto;
import com.the.raven.tech.task.dto.UpdateCustomerDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerResponseDto createCustomer(@Valid CreateCustomerDto customer);

    Optional<CustomerResponseDto> getCustomerById(Long id);

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto updateCustomer(Long id, @Valid UpdateCustomerDto updatedCustomer);

    void deleteCustomer(Long id);
}
