package com.trackmyfix.trackmyfix.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.trackmyfix.trackmyfix.utils.MessagesUtils;
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

    @NotBlank(message = "{order.number.mandatory}")
    @Size(max = 25, message = "{order.number.max_length}")
    @Column(nullable = false, unique = true, length = 25)
    private String number;

    @Size(max = 65535, message = "{order.observation.max_length}")
    @Column(columnDefinition = "TEXT")
    private String observations;

    @NotNull(message = "{order.observation.max_length}")
    @PositiveOrZero(message = "{order.total.positive_or_zero}")
    @Column(precision = 10, scale = 2)
    private BigDecimal orderTotal;

    @ManyToOne
    @NotNull(message = "{client.required}")
    @JoinColumn(name = "id_client", referencedColumnName = "id_user")
    private Client client;

    @NotNull(message = "{active.mandatory}")
    @Column(nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Size(max = 1, message = "{device.exactly.one}")
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