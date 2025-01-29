package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.DeviceRepository;
import com.trackmyfix.trackmyfix.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@AllArgsConstructor
public class DeviceDataLoader {

    private final DeviceRepository deviceRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void load() {
        List<Order> orders = (List<Order>) orderRepository.findAll();

        if (orders.size() < 2) {
            throw new IllegalStateException("Se necesitan al menos 2 órdenes en la base de datos");
        }

        Order order1 = orders.get(0);
        Order order2 = orders.get(1);
        Order order3 = orders.get(2);
        Order order4 = orders.get(3);
        Order order5 = orders.get(4);
        Order order6 = orders.get(5);
        Order order7 = orders.get(6);
        Order order8 = orders.get(7);
        Order order9 = orders.get(8);
        Order order10 = orders.get(9);

        // Dispositivos para la primera orden
        Device device1 = new Device(null, "Dell OptiPlex 3080", "SN-00001", "Teclado y Mouse", new BigDecimal(3000), new BigDecimal(30000), "No enciende", "Fuente dañada", order1, Type.COMPUTADORA_DE_ESCRITORIO, State.RECIBIDO, null, null);
        Device device2 = new Device(null, "MacBook Pro 2020", "SN-00002", "Cargador original", new BigDecimal(2000), new BigDecimal(20000), "Pantalla con rayas", "Falla en GPU", order1, Type.NOTEBOOK, State.RECIBIDO, null, null);

        // Dispositivos para la segunda orden
        Device device3 = new Device(null, "HP Pavilion 15", "SN-00003", "Cargador genérico", new BigDecimal(8000), new BigDecimal(80000), "No carga", "Batería agotada", order2, Type.NOTEBOOK, State.EN_DIAGNOSTICO, null, null);
        Device device4 = new Device(null, "Samsung Galaxy S21", "SN-00004", "Sin accesorios", new BigDecimal(9000), new BigDecimal(90000), "No enciende", "Falla en la placa", order2, Type.SMARTPHONE, State.EN_DIAGNOSTICO, null, null);

        // Dispositivos para el resto de las órdenes
        Device device5 = new Device(null, "Dell Inspiron 5000", "SN-00005", "Teclado y Mouse", new BigDecimal(10000), new BigDecimal(100000), "No enciende", "Fuente dañada", orders.get(2), Type.COMPUTADORA_DE_ESCRITORIO, State.EN_REPARACION, null, null);
        Device device6 = new Device(null, "iPhone 13", "SN-00006", "Cargador", new BigDecimal(15000), new BigDecimal(150000), "Pantalla dañada", "Falla en la cámara", orders.get(3), Type.SMARTPHONE, State.EN_REPARACION, null, null);
        Device device7 = new Device(null, "Samsung Galaxy Tab S7", "SN-00007", "Cargador", new BigDecimal(15000), new BigDecimal(150000), "Pantalla rota", "Batería dañada", orders.get(4), Type.TABLET, State.LISTO_PARA_RETIRO, null, null);
        Device device8 = new Device(null, "Lenovo ThinkPad X1 Carbon", "SN-00008", "Mouse inalámbrico", new BigDecimal(50000), new BigDecimal(500000), "No carga", "Placa base dañada", orders.get(5), Type.NOTEBOOK, State.ENTREGADO, null, null);
        Device device9 = new Device(null, "MacBook Air 2020", "SN-00009", "Cargador original", new BigDecimal(100000), new BigDecimal(1000000), "Pantalla parpadeante", "Problema en la GPU", orders.get(6), Type.NOTEBOOK, State.RECIBIDO, null, null);
        Device device10 = new Device(null, "Sony Xperia 5", "SN-00010", "Cargador", new BigDecimal(7000), new BigDecimal(70000), "No enciende", "Falla en la placa base", orders.get(7), Type.SMARTPHONE, State.CANCELADO, null, null);
        Device device11 = new Device(null, "HP Envy 13", "SN-00011", "Teclado Bluetooth", new BigDecimal(6000), new BigDecimal(60000), "No carga", "Fuente de alimentación dañada", orders.get(8), Type.NOTEBOOK, State.RECIBIDO, null, null);
        Device device12 = new Device(null, "Google Pixel 5", "SN-00012", "Cargador original", new BigDecimal(20000), new BigDecimal(20000), "Pantalla rota", "Problema con el sensor", orders.get(9), Type.SMARTPHONE, State.RECIBIDO, null, null);

        deviceRepository.saveAll(List.of(device1, device2, device3, device4, device5, device6));

        order1.getDevices().add(device1);
        order2.getDevices().add(device2);
        order3.getDevices().add(device10);
        order4.getDevices().add(device5);
        order5.getDevices().add(device6);
        order6.getDevices().add(device7);
        order7.getDevices().add(device8);
        order8.getDevices().add(device9);
        order9.getDevices().add(device11);
        order10.getDevices().add(device12);

        order1.updateOrderTotal();
        order2.updateOrderTotal();
        order3.updateOrderTotal();
        order4.updateOrderTotal();
        order5.updateOrderTotal();
        order6.updateOrderTotal();
        order7.updateOrderTotal();
        order8.updateOrderTotal();
        order9.updateOrderTotal();
        order10.updateOrderTotal();



        orderRepository.saveAll(orders);
    }
}