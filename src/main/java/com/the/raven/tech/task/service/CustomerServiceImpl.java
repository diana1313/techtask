package com.the.raven.tech.task.service;

import com.the.raven.tech.task.domain.Customer;
import com.the.raven.tech.task.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private static final String ID_NOT_FOUND_MESSAGE = "Customer with given id is not found: ";

    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id).map(customer -> {
            customer.setFullName(updatedCustomer.getFullName());
            customer.setPhone(updatedCustomer.getPhone());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException(ID_NOT_FOUND_MESSAGE + id));
    }

    public void deleteCustomer(Long id) {
        customerRepository.findById(id).map(customer -> {
            customer.setActive(false);
            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException(ID_NOT_FOUND_MESSAGE + id));
    }
}
