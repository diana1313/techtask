package com.the.raven.tech.task.dto;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@Builder
public class CustomerDto {
    @NonNull
    private Long id;

    @Size(min = 2, max = 50)
    @NonNull
    private String fullName;

    @Email
    @Size(min = 2, max = 100)
    @NonNull
    private String email;

    @Pattern(regexp = "\\+\\d{6,14}")
    private String phone;
}
