package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("Client")
@NoArgsConstructor
public class Client extends User {
    public Client(Long id, Role role, String name, String lastName, String dni, String address, String phone,
                  String email, Boolean active, Date createdAt, Date updatedAt) {
        super(id, role, name, lastName, dni, address, phone, email, null, active, createdAt, updatedAt);
    }
}
