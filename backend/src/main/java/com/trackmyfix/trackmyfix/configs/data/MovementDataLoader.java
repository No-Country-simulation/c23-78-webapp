package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class MovementDataLoader {

    private final MovementRepository movementRepository;
    private final TechnicianRepository technicianRepository;
    private final OrderRepository orderRepository;
    private final DeviceRepository deviceRepository;
    private final ActionRepository actionRepository;

    public void load() {
        List<Technician> technicians = (List<Technician>) technicianRepository.findAll();
        List<Order> orders = (List<Order>) orderRepository.findAll();
        List<Device> devices = (List<Device>) deviceRepository.findAll();
        List<Action> actions = (List<Action>) actionRepository.findAll();
        Movement movement1 = new Movement(null, technicians.get(0), orders.get(0), devices.get(0), new Date(), actions.get(0), "Description1");
        Movement movement2 = new Movement(null, technicians.get(1), orders.get(1), devices.get(1), new Date(), actions.get(1), "Description2");
        Movement movement3 = new Movement(null, technicians.get(2), orders.get(2), devices.get(2), new Date(), actions.get(2), "Description3");
        Movement movement4 = new Movement(null, technicians.get(0), orders.get(3), devices.get(3), new Date(), actions.get(3), "Description4");
        Movement movement5 = new Movement(null, technicians.get(1), orders.get(4), devices.get(4), new Date(), actions.get(0), "Description5");
        Movement movement6 = new Movement(null, technicians.get(2), orders.get(5), devices.get(5), new Date(), actions.get(1), "Description6");
        Movement movement7 = new Movement(null, technicians.get(0), orders.get(6), devices.get(6), new Date(), actions.get(2), "Description7");
        Movement movement8 = new Movement(null, technicians.get(1), orders.get(7), devices.get(7), new Date(), actions.get(3), "Description8");
        movementRepository.saveAll(List.of(movement1, movement2, movement3, movement4, movement5, movement6, movement7, movement8));
    }
}
