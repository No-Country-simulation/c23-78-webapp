package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@DiscriminatorValue("Client")
@NoArgsConstructor
public class Client extends User {
}
