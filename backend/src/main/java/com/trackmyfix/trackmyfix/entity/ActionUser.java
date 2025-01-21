package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class ActionUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActionUser;

    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
