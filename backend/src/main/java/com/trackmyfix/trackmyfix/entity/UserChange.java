package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.*;
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
public class UserChange implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserChange;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActionUser actionUser;

    @ManyToOne
    @JoinColumn(name = "id_technician")
    private Technician technician;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
