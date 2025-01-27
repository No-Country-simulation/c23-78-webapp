package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.entity.Client;
import com.trackmyfix.trackmyfix.entity.Device;
import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.repository.ClientRepository;
import com.trackmyfix.trackmyfix.repository.DeviceRepository;
import com.trackmyfix.trackmyfix.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderDataLoader {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    public void load() {
        List<Client> clients = (List<Client>) clientRepository.findAll();

        if (clients.size() < 8) {
            throw new IllegalStateException("No hay suficientes clientes en la base de datos.");
        }

        List<Order> orders = List.of(
                new Order(null, "ORD-00001", "Observation1",  new BigDecimal(0), clients.get(0), true, null, null, new ArrayList<>()),
                new Order(null, "ORD-00002", "Observation2",  new BigDecimal(0), clients.get(1), true, null, null, new ArrayList<>()),
                new Order(null, "ORD-00003", "Observation3",  new BigDecimal(0), clients.get(2), true, null, null, new ArrayList<>()),
                new Order(null, "ORD-00004", "Observation4",  new BigDecimal(0), clients.get(3), true, null, null, new ArrayList<>()),
                new Order(null, "ORD-00005", "Observation5",  new BigDecimal(0), clients.get(4), true, null, null, new ArrayList<>()),
                new Order(null, "ORD-00006", "Observation6",  new BigDecimal(0), clients.get(5), true, null, null, new ArrayList<>()),
                new Order(null, "ORD-00007", "Observation7",  new BigDecimal(0), clients.get(6), true, null, null, new ArrayList<>()),
                new Order(null, "ORD-00008", "Observation8",  new BigDecimal(0), clients.get(7), true, null, null, new ArrayList<>()),
                new Order(null, "ORD-00009", "Observation9", new BigDecimal(0), clients.get(0), false, null, null, new ArrayList<>()),
                new Order(null, "ORD-00010", "Observation10",  new BigDecimal(0), clients.get(1), false, null, null, new ArrayList<>())
        );


        orderRepository.saveAll(orders);
    }
}
