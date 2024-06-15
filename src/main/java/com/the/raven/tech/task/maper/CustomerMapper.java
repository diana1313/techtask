package com.the.raven.tech.task.maper;

import com.the.raven.tech.task.domain.Customer;
import com.the.raven.tech.task.dto.CreateCustomerDto;
import com.the.raven.tech.task.dto.CustomerResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

  public Customer toEntity(CreateCustomerDto dto) {
    var newEntity = new Customer();
    newEntity.setEmail(dto.getEmail());
    newEntity.setPhone(dto.getPhone());
    newEntity.setFullName(dto.getFullName());
    return newEntity;
  }

  public CustomerResponseDto toCustomerResponseDto(Customer customer) {
    return CustomerResponseDto.builder()
        .id(customer.getId())
        .fullName(customer.getFullName())
        .email(customer.getEmail())
        .phone(customer.getPhone())
        .build();
  }
}
