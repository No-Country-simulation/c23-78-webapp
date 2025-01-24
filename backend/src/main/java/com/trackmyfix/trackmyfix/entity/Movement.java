package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovement;


    @ManyToOne
    @JoinColumn(name = "id_technician", referencedColumnName = "id_user")
    private Technician technician;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_device")
    private Device device;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date movementDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Action action;

    @Column(columnDefinition = "TEXT")
    private String description;

    @PrePersist
    protected void onCreate() {
        movementDate = new Date();
    }
}
