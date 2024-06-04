package com.the.raven.tech.task.service;

import com.the.raven.tech.task.domain.Customer;
import com.the.raven.tech.task.dto.CustomerDto;
import com.the.raven.tech.task.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerDto createCustomer(Customer customer) {
        customerRepository.save(customer);
        return convertToDTO(customer);

    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CustomerDto> getCustomerById(Long id) {
        return customerRepository.findById(id).map(this::convertToDTO);
    }

    public CustomerDto updateCustomer(Long id, Customer updatedCustomer) {
        if (customerRepository.updateCustomer(id,
                updatedCustomer.getFullName(), updatedCustomer.getPhone()) != 0) {
            return convertToDTO(updatedCustomer);
        } else {
            throw new RuntimeException("Customer with given id is not found: " + id);
        }
    }

    public void deleteCustomer(Long id) {
        customerRepository.softDelete(id);
    }

    private CustomerDto convertToDTO(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }
}

