package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Client")
@NoArgsConstructor
public class Client extends User {
}
