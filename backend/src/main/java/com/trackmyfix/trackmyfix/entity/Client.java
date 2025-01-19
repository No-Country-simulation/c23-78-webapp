package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.SuperBuilder;
import lombok.AllArgsConstructor;

@Entity
@DiscriminatorValue("Client")
@SuperBuilder
@AllArgsConstructor
public class Client extends User {
}
