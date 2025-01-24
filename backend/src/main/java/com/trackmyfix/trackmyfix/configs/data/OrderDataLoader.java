package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.entity.Client;
import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.repository.ClientRepository;
import com.trackmyfix.trackmyfix.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderDataLoader {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;



    public void load() {
        List<Client> clients = (List<Client>) clientRepository.findAll();

        Order order1 = new Order(null, "ORD-0001", "Observation1", new BigDecimal(0), new BigDecimal(1000), clients.get(0),true, null, null);
        Order order2 = new Order(null, "ORD-0002", "Observation2", new BigDecimal(2000), new BigDecimal(0), clients.get(1), true, null, null);
        Order order3 = new Order(null, "ORD-0003", "Observation3", new BigDecimal(500), new BigDecimal(1500), clients.get(2), true, null, null);
        Order order4 = new Order(null, "ORD-0004", "Observation4", new BigDecimal(800), new BigDecimal(2000), clients.get(3), true, null, null);
        Order order5 = new Order(null, "ORD-0005", "Observation5", new BigDecimal(1200), new BigDecimal(2500), clients.get(4),true,  null, null);
        Order order6 = new Order(null, "ORD-0006", "Observation6", new BigDecimal(300), new BigDecimal(1700), clients.get(5), true, null, null);
        Order order7 = new Order(null, "ORD-0007", "Observation7", new BigDecimal(600), new BigDecimal(2200), clients.get(6), true, null, null);
        Order order8 = new Order(null, "ORD-0008", "Observation8", new BigDecimal(900), new BigDecimal(2700), clients.get(7), true, null, null);
        Order order9 = new Order(null, "ORD-0009", "Observation9", new BigDecimal(400), new BigDecimal(1900), clients.get(0), false, null, null);
        Order order10 = new Order(null, "ORD-0010", "Observation10", new BigDecimal(1100), new BigDecimal(3000), clients.get(1), false, null, null);

        orderRepository.saveAll(List.of(order1, order2, order3, order4, order5, order6, order7, order8, order9, order10));
    }
}
