package com.trackmyfix.trackmyfix.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @NotBlank(message = "The work order number is mandatory")
    @Size(max = 25, message = "Work order number cannot exceed 25 characters")
    @Column(nullable = false, unique = true, length = 25)
    private String number;

    @Size(max = 65535, message = "Observations cannot exceed 65535 characters")
    @Column(columnDefinition = "TEXT")
    private String observations;

    @NotNull(message = "Order total is mandatory")
    @PositiveOrZero(message = "Order total must be positive or zero")
    @Column(precision = 10, scale = 2)
    private BigDecimal orderTotal;

    @ManyToOne
    @NotNull(message = "Client is required")
    @JoinColumn(name = "id_client", referencedColumnName = "id_user")
    private Client client;

    @NotNull(message = "Active status is mandatory")
    @Column(nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Size(max = 1, message = "There must be exactly one device")
    @JsonManagedReference
    private List<Device> devices = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
        updateOrderTotal();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
        updateOrderTotal();
    }

    public void updateOrderTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (Device device : devices) {
            total = total.add(device.getInitialPrice()).add(device.getFinalPrice());
        }
        this.orderTotal = total;
    }
}