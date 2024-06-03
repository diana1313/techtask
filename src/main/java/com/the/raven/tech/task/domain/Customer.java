package com.the.raven.tech.task.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Entity(name = "Customers")
@Table(name = "customers")
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
    private Long created;

    @Column(nullable = false)
    @UpdateTimestamp
    private Long updated;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "full_name")
    private String fullName;

    @Email
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^\\+?\\d{6,14}$")
    @Column(nullable = true) //what does this nullable mean?
    private String phone;

    @Column(name = "is_active", nullable = false) //what is the difference between nullable = false and @NotBlank
    private boolean isActive = true;
}

