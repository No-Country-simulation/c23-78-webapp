package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Getter;
import lombok.Setter;


@Entity
@DiscriminatorValue("Technician")
@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
public class Technician extends User {
    @Column(length = 100)
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;
}
