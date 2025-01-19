package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@DiscriminatorValue("Technician")
@NoArgsConstructor
public class Technician extends User {

    private String password;

}
