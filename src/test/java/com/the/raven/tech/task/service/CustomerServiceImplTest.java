package com.the.raven.tech.task.service;

import com.the.raven.tech.task.domain.Customer;
import com.the.raven.tech.task.dto.CreateCustomerDto;
import com.the.raven.tech.task.dto.CustomerResponseDto;
import com.the.raven.tech.task.dto.UpdateCustomerDto;
import com.the.raven.tech.task.maper.CustomerMapper;
import com.the.raven.tech.task.repo.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void createCustomer() {
        // Given
        var customer = mock(CreateCustomerDto.class);
        var savedEntity = mock(Customer.class);
        var response = mock(CustomerResponseDto.class);
        when(customerMapper.toEntity(customer)).thenReturn(savedEntity);
        when(customerRepository.save(notNull())).thenReturn(savedEntity);
        when(customerMapper.toCustomerResponseDto(savedEntity)).thenReturn(response);


        // When
        CustomerResponseDto createdCustomer = customerService.createCustomer(customer);

        // Then
        assertNotNull(createdCustomer);
        assertSame(response, createdCustomer);
    }

    @Test
    void getAllCustomers() {
        // When
        var customer = mock(Customer.class);
        when(customerRepository.findAllActive()).thenReturn(List.of(customer));
        var resultDto = mock(CustomerResponseDto.class);
        when(customerMapper.toCustomerResponseDto(customer)).thenReturn(resultDto);

        List<CustomerResponseDto> result = customerService.getAllCustomers();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(resultDto, result.get(0));
    }

    @Test
    void getCustomerById() {
        // Given
        var id = 22L;
        var customer = mock(Customer.class);
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        var responseDto = mock(CustomerResponseDto.class);
        when(customerMapper.toCustomerResponseDto(customer)).thenReturn(responseDto);

        // When
        Optional<CustomerResponseDto> resultOpt = customerService.getCustomerById(id);

        assertTrue(resultOpt.isPresent());
        var result = resultOpt.get();
        assertSame(responseDto, result);
    }

    @Test
    void updateCustomer() {
        // Given
        var id = 23L;
        var updatedName = "John Smith";
        var updatedPhone = "+000000000";
        var dto = new UpdateCustomerDto(updatedName, updatedPhone);
        var customer = mock(Customer.class);
        when(customerRepository.updateCustomer(id, updatedName, updatedPhone)).thenReturn(customer);
        var responseDto = mock(CustomerResponseDto.class);
        when(customerMapper.toCustomerResponseDto(customer)).thenReturn(responseDto);

        // When
        var result = customerService.updateCustomer(id, dto);

        // Then
        assertSame(responseDto, result);
    }

    @Test
    void deleteCustomer() {
        // Given
       var id = 102L;

        // When
       customerService.deleteCustomer(id);

       // Then
       verify(customerRepository).deleteById(id);
    }
}


