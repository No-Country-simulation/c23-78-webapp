package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("Technician")
@NoArgsConstructor
public class Technician extends User {

    private String password;

    public Technician(Long id, Role role, String name, String lastName, String dni, String address, String phone, String email, String password, Boolean active, Date createdAt, Date updatedAt, String password1) {
        super(id, role, name, lastName, dni, address, phone, email, password, active, createdAt, updatedAt);
        this.password = password1;
    }
}
