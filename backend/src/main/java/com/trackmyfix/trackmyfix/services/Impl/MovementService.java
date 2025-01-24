package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.entity.Action;
import com.trackmyfix.trackmyfix.entity.Movement;
import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.entity.Technician;
import com.trackmyfix.trackmyfix.event.OrderCreatedEvent;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.MovementRepository;
import com.trackmyfix.trackmyfix.repository.TechnicianRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovementService {
    private final MovementRepository movementRepository;
    private final TechnicianRepository technicianRepository;

    @Async
    @Transactional
    @EventListener
    public void handleOrderCreatedEvent(OrderCreatedEvent event){
        Order order = event.getOrder();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Technician technician = technicianRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Técnico con email " + email + " no encontrado"));

        Movement movement = new Movement();
        movement.setOrder(order);
        movement.setDescription(order.getCreatedAt()+": "+order.getNumber()+"// " + order.getObservations());
        movement.setAction(Action.CREO_ORDEN_TRABAJO);
        movement.setTechnician(technician);

        movementRepository.save(movement);
    }
}
