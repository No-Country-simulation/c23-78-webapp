package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Movement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovement;


    @ManyToOne
    @JoinColumn(name = "id_technician", referencedColumnName = "id_user")
    private Technician technician;

    @OneToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @OneToOne
    @JoinColumn(name = "id_device")
    private Device device;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date movementDate;

    @ManyToOne
    @JoinColumn(name = "id_action")
    private Action action;

    @Column(columnDefinition = "TEXT")
    private String description;

    @PrePersist
    protected void onCreate() {
        movementDate = new Date();
    }
}
