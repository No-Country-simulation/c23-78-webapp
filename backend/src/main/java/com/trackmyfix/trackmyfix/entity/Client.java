package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;

import java.util.Date;

@Entity
@DiscriminatorValue("Client")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE user SET active = 0 WHERE id_user=?")
public class Client extends User {
    private String password;
}
