package com.the.raven.tech.task.dto;

import lombok.*;
import org.springframework.lang.NonNull;

@Data
@Getter
@Setter
@Builder
public class CustomerDto {
    @NonNull
    private Long id;

    @NonNull
    private String fullName;

    @NonNull
    private String email;

    private String phone;
}
