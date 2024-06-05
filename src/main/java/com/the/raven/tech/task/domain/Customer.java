package com.the.raven.tech.task.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.io.Serializable;
import java.time.Instant;

@Entity(name = "Customers")
@Table(name = "customers")
@SQLDelete(sql = "UPDATE customers SET is_active = false WHERE id=?")
@FilterDef(name = "activeCustomers", parameters = @ParamDef(name = "isActive", type = Boolean.class))
@Filter(name = "activeCustomers", condition = "is_active = :isActive")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant created;

    @Column(nullable = false)
    @UpdateTimestamp
    private Instant updated;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "full_name")
    private String fullName;

    @Email
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^\\+\\d{6,14}$")
    @Column(nullable = true)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = Boolean.TRUE;
}

