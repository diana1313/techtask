package com.the.raven.tech.task.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CustomerDto {
    @NonNull
    private Long id;
    @NonNull
    private String fullName;
    @NonNull
    private String email;
    @NonNull
    private String phone;
}
