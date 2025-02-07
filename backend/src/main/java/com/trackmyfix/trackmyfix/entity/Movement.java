package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovement;

    @NotNull(message = "{movement.technician.mandatory}")
    @ManyToOne
    @JoinColumn(name = "id_technician", referencedColumnName = "id_user")
    private Technician technician;

    @NotNull(message = "{movement.order.mandatory}")
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @NotNull(message = "{movement.device.mandatory}")
    @ManyToOne
    @JoinColumn(name = "id_device")
    private Device device;

    @NotNull(message = "{movement.date.mandatory}")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date movementDate;

    @NotNull(message = "{movement.action.mandatory}")
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
