package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class UserChanges implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserChange;

    @ManyToOne
    @JoinColumn(name = "id_action_user")
    private ActionUser actionUser;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
