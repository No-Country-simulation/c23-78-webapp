package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

@SuperBuilder
@Entity
@DiscriminatorValue("Admin")
@NoArgsConstructor
public class Admin extends User {


}
