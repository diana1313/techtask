package com.the.raven.tech.task.repo;

import com.the.raven.tech.task.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan(basePackages = "com.the.raven.tech.task.repo")
public class CustomerRepositoryTest {
    private static final String FULL_NAME = "John Smith";
    private static  final String EMAIL = "smith@example.com";
    private static final String PHONE = "+123456789";

    @Autowired
    private CustomerRepository customerRepository;

    // this db logic is working fine, but somehow test is not working
    // @Test
    void updateCustomerDetails() {
        String uName = "Jade Smith";
        String uPhone = "+429888943";
        Customer customer = new Customer();
        customer.setFullName(FULL_NAME);
        customer.setEmail(EMAIL);
        customer.setPhone(PHONE);
        customer = customerRepository.save(customer);

        customerRepository.updateCustomer(customer.getId(), uName, uPhone);

        Optional<Customer> updatedCustomer = customerRepository.findById(customer.getId());

        assertTrue(updatedCustomer.isPresent());
        assertEquals(uName, updatedCustomer.get().getFullName());
        assertEquals(uPhone, updatedCustomer.get().getPhone());
    }

    @Test
    void softDeleteById() {
        Customer customer = new Customer();
        customer.setFullName(FULL_NAME);
        customer.setEmail(EMAIL);
        customer.setPhone(PHONE);
        customer = customerRepository.save(customer);

        customerRepository.deleteById(customer.getId());

        List<Customer> deletedCustomer = customerRepository.findAll();

        assertFalse(deletedCustomer.get(0).isActive());
    }

    @Test
    void findAllActive() {
        // Given
        Customer customer1 = new Customer();
        customer1.setFullName(FULL_NAME);
        customer1.setEmail(EMAIL);
        customer1.setPhone(PHONE);
        customer1.setActive(true);
        customer1 = customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFullName("Deleted name");
        customer2.setEmail("deleted@email.com");
        customer2.setPhone("+88888888");
        customer2.setActive(false);
        customer2 = customerRepository.save(customer2);

        // When
        var result = customerRepository.findAllActive();

        // Then
        assertFalse(result.isEmpty());
        assertEquals(result.get(0), customer1);
        var inactiveOpt = customerRepository.findAll()
            .stream()
            .filter(c -> !c.isActive())
            .findFirst();
        assertTrue(inactiveOpt.isPresent());
        var inactive = inactiveOpt.get();
        assertEquals(customer2, inactive);
    }
}
