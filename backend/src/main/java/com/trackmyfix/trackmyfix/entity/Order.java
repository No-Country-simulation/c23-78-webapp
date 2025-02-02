package com.trackmyfix.trackmyfix.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
    @Column(nullable = false, unique = true, length = 25)
    private String number;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @NotNull(message = "Order total is mandatory")
    @PositiveOrZero(message = "Value must be positive or zero")
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