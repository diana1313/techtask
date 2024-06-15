package com.the.raven.tech.task.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Data
@Getter
@Setter
@Builder
public class CustomerResponseDto {
    @NonNull
    private Long id;

    @NonNull
    private String fullName;

    @NonNull
    private String email;

    private String phone;
}
