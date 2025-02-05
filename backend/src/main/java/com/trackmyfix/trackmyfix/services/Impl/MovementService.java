package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.event.DeviceCreateEvent;
import com.trackmyfix.trackmyfix.event.DeviceStateChangeEvent;
import com.trackmyfix.trackmyfix.event.OrderCreateEvent;
import com.trackmyfix.trackmyfix.event.OrderUpdateEvent;
import com.trackmyfix.trackmyfix.exceptions.DeviceNotFoundException;
import com.trackmyfix.trackmyfix.exceptions.MovementNotFoundException;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.MovementRepository;
import com.trackmyfix.trackmyfix.repository.TechnicianRepository;
import com.trackmyfix.trackmyfix.services.IMovementService;
import com.trackmyfix.trackmyfix.utils.MovementUtils;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovementService implements IMovementService {
    private final MovementRepository movementRepository;
    private final TechnicianRepository technicianRepository;

    @Async
    @Transactional
    @EventListener
    public void handleOrderCreatedEvent(DeviceCreateEvent event) {
        for (Device device : event.getOrder().getDevices()) {
            Order order = event.getOrder();
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Technician technician = technicianRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Technician with email " + email + " not found"));

            Movement movement = new Movement();
            movement.setOrder(order);
            movement.setOrder(order);
            movement.setDescription(MovementUtils.generateDescription(order, event.getChanges()));
            movement.setAction(event.getAction());
            movement.setTechnician(technician);
            movement.setDevice(device);

            movementRepository.save(movement);
        }

    }

    @Async
    @Transactional
    @EventListener
    public void handleOrderCreatedEvent(OrderCreateEvent event) {

        Order order = event.getOrder();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Technician technician = technicianRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Technician with email " + email + " not found"));

        Movement movement = new Movement();
        movement.setOrder(order);
        movement.setDescription(MovementUtils.generateDescription(order, new HashMap<>()));
        movement.setAction(event.getAction());
        movement.setTechnician(technician);
        movement.setDevice(event.getOrder().getDevices().get(0));

        movementRepository.save(movement);
    }

    @Async
    @Transactional
    @EventListener
    public void handleOrderCreatedEvent(OrderUpdateEvent event) {

        Order order = event.getOrder();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Technician technician = technicianRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Technician with email " + email + " not found"));

        Movement movement = new Movement();
        movement.setOrder(order);
        movement.setDescription(MovementUtils.generateDescription(order, event.getChanges()));
        movement.setAction(event.getAction());
        movement.setTechnician(technician);
        movement.setDevice(event.getOrder().getDevices().get(0));

        movementRepository.save(movement);
    }

    @Async
    @Transactional
    @EventListener
    public void handleDeviceStateChange(DeviceStateChangeEvent event) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Technician technician = technicianRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Technician with email " + email + " not found"));

        Movement movement = Movement.builder()
                .device(event.getDevice())
                .order(event.getDevice().getOrder())
                .technician(technician)
                .description(event.getDescription())
                .action(event.getAction())
                .build();

        movementRepository.save(movement);
    }

    @Override
    @Transactional
    public Map<String, Object> findAll() {
        List<Movement> movements = (List<Movement>) movementRepository.findAll();
        return Map.of("movements", movements, "orderSize", movements.size());
    }

    @Override
    @Transactional
    public List<Movement> findByAction(Action action) {
        List<Movement> movements = movementRepository.findByAction(action);
        if (movements.isEmpty()) {
            throw new DeviceNotFoundException("No movements with Action " + action.name() + " found");
        }
        return movements;
    }

    @Override
    @Transactional
    public Movement findById(Long id) {
        return movementRepository.findById(id).orElseThrow(
                () -> new MovementNotFoundException("Movement with ID " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllAction() {
        return Arrays.stream(Action.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
