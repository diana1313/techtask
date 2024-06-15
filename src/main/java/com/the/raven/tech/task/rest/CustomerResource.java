package com.the.raven.tech.task.rest;

import com.the.raven.tech.task.domain.Customer;
import com.the.raven.tech.task.dto.CreateCustomerDto;
import com.the.raven.tech.task.dto.CustomerResponseDto;
import com.the.raven.tech.task.dto.UpdateCustomerDto;
import com.the.raven.tech.task.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerResource {
    private final CustomerServiceImpl customerService;

    @PostMapping
    public CustomerResponseDto createCustomer(@Valid @RequestBody CreateCustomerDto customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping
    public List<CustomerResponseDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable("id") Long id, @Valid @RequestBody UpdateCustomerDto updatedCustomer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, updatedCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
