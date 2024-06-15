package com.the.raven.tech.task.service;

import com.the.raven.tech.task.dto.CreateCustomerDto;
import com.the.raven.tech.task.dto.CustomerResponseDto;
import com.the.raven.tech.task.dto.UpdateCustomerDto;
import com.the.raven.tech.task.maper.CustomerMapper;
import com.the.raven.tech.task.repo.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponseDto createCustomer(CreateCustomerDto customer) {
        var entity = customerMapper.toEntity(customer);
        customerRepository.save(entity);
        return customerMapper.toCustomerResponseDto(entity);

    }

    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAllActive()
            .stream()
            .map(customerMapper::toCustomerResponseDto)
            .collect(Collectors.toList());
    }

    public Optional<CustomerResponseDto> getCustomerById(Long id) {
        return customerRepository.findById(id).map(customerMapper::toCustomerResponseDto);
    }

    public CustomerResponseDto updateCustomer(Long id, UpdateCustomerDto updateCustomerDto) {
        var updatedCustomer = customerRepository.updateCustomer(id,
            updateCustomerDto.getFullName(), updateCustomerDto.getPhone());
        if (updatedCustomer != null) {
            return customerMapper.toCustomerResponseDto(updatedCustomer);
        } else {
            throw new RuntimeException("Customer with given id is not found: " + id);
        }
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}

