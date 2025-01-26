package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.entity.Movement;
import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.entity.Technician;
import com.trackmyfix.trackmyfix.event.OrderEvent;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.MovementRepository;
import com.trackmyfix.trackmyfix.repository.TechnicianRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovementService {
    private final MovementRepository movementRepository;
    private final TechnicianRepository technicianRepository;

    @Async
    @Transactional
    @EventListener
    public void handleOrderCreatedEvent(OrderEvent event) {
        Order order = event.getOrder();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Technician technician = technicianRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Técnico con email " + email + " no encontrado"));

        Movement movement = new Movement();
        movement.setOrder(order);
        movement.setOrder(order);
        movement.setDescription(this.generateDescription(order, event.getChanges()));
        movement.setAction(event.getAction());
        movement.setTechnician(technician);

        movementRepository.save(movement);
    }

    private String generateDescription(Order order, Map<String, Object> changes) {
        String changesDescription = changes.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(", "));
        return "[" + order.getNumber() + "] =>" + " Cambios: [" + changesDescription + "]";
    }
}
