package com.the.raven.tech.task.service;

import com.the.raven.tech.task.domain.Customer;
import com.the.raven.tech.task.dto.CustomerDto;
import com.the.raven.tech.task.repo.CustomerRepository;
import com.the.raven.tech.task.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ComponentScan(basePackages = "com.the.raven.tech.task.service")
@DataJpaTest
class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    private static final String FULL_NAME = "John Smith";
    private static final String EMAIL = "smith@example.com";
    private static final String PHONE = "+123456789";


    @Test
    void createCustomer() {
        Customer customer = new Customer();
        customer.setFullName(FULL_NAME);
        customer.setEmail(EMAIL);
        customer.setPhone(PHONE);

        CustomerDto createdCustomer = customerService.createCustomer(customer);

        assertNotNull(createdCustomer);
        assertEquals(FULL_NAME, createdCustomer.getFullName());
        assertEquals(EMAIL, createdCustomer.getEmail());
        assertEquals(PHONE, createdCustomer.getPhone());
    }

    @Test
    void getAllCustomers() {
        Customer customer = new Customer();
        customer.setFullName(FULL_NAME);
        customer.setEmail(EMAIL);
        customer.setPhone(PHONE);
        customer.setId(Long.valueOf(2));
        customerRepository.save(customer);

        List<CustomerDto> customers = customerService.getAllCustomers();
        assertNotNull(customers);
        assertFalse(customers.isEmpty());
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer();
        customer.setFullName(FULL_NAME);
        customer.setEmail(EMAIL);
        customer.setPhone(PHONE);
        customer.setId(Long.valueOf(2));
        customer = customerRepository.save(customer);

        Optional<CustomerDto> retrievedCustomer = customerService.getCustomerById(customer.getId());

        assertTrue(retrievedCustomer.isPresent());
        assertEquals(FULL_NAME, retrievedCustomer.get().getFullName());
    }

    @Test
    void updateCustomer() {
        String uFullName = "Jade Smith";
        String uPhone = "+429888943";
        Customer customer = new Customer();
        customer.setFullName(FULL_NAME);
        customer.setEmail(EMAIL);
        customer.setPhone(PHONE);
        customer = customerRepository.save(customer);

        Customer uCustomer = new Customer();
        uCustomer.setFullName(uFullName);
        uCustomer.setPhone(uPhone);
        uCustomer.setEmail(EMAIL);
        uCustomer.setId(customer.getId());

        CustomerDto updatedCustomer = customerService.updateCustomer(uCustomer.getId(), uCustomer);

        assertNotNull(updatedCustomer);
        assertEquals(uFullName, updatedCustomer.getFullName());
        assertEquals(EMAIL, updatedCustomer.getEmail());
        assertEquals(uPhone, updatedCustomer.getPhone());
    }

   @Test
    void deleteCustomer() {
        Customer customer = new Customer();
        customer.setFullName(FULL_NAME);
        customer.setEmail(EMAIL);
        customer.setPhone(PHONE);
        customer = customerRepository.save(customer);

        customerService.deleteCustomer(customer.getId());

        List<Customer> deletedCustomer = customerRepository.findAll();

        assertFalse(deletedCustomer.get(0).isActive());
    }
}


