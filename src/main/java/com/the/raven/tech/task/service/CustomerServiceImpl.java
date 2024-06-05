package com.the.raven.tech.task.service;

import com.the.raven.tech.task.domain.Customer;
import com.the.raven.tech.task.dto.CustomerDto;
import com.the.raven.tech.task.repo.CustomerRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private EntityManager entityManager;

    public CustomerDto createCustomer(Customer customer) {
        customerRepository.save(customer);
        return convertToDTO(customer);

    }

    public List<CustomerDto> getAllCustomers() {
        return findAll().stream()
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
        customerRepository.deleteById(id);
    }

    private CustomerDto convertToDTO(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }

    private List<Customer> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("activeCustomers");
        filter.setParameter("isActive", Boolean.TRUE);
        List<Customer> customers = customerRepository.findAll();
        session.disableFilter("deletedProductFilter");
        return customers;
    }
}

