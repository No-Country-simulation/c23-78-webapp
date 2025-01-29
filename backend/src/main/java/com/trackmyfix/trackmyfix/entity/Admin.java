package com.trackmyfix.trackmyfix.entity;


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;


@Entity
@DiscriminatorValue("Admin")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE user SET active = 0 WHERE id_user=?")
public class Admin extends User {
    @Column(length = 100)
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;
}
