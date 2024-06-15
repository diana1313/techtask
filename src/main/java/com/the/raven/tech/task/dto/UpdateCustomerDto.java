package com.the.raven.tech.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateCustomerDto {
  @NotBlank
  @Size(min = 2, max = 50)
  private final String fullName;

  @Pattern(regexp = "^\\+\\d{6,14}$")
  private final String phone;
}
