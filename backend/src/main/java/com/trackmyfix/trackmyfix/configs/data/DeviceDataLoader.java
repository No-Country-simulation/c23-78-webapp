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

        if (orders.size() < 10) { // Asegúrate de tener al menos 10 órdenes
            throw new IllegalStateException("Se necesitan al menos 10 órdenes en la base de datos");
        }

        // Crea los dispositivos con las órdenes correspondientes
        Device device1 = new Device(null, "Dell OptiPlex 3080", "SN-00001", "Teclado y Mouse", new BigDecimal(3000), new BigDecimal(30000), "No enciende", "Fuente dañada", orders.get(0), Type.COMPUTADORA_DE_ESCRITORIO, State.RECIBIDO, null, null);
        Device device2 = new Device(null, "MacBook Pro 2020", "SN-00002", "Cargador original", new BigDecimal(2000), new BigDecimal(20000), "Pantalla con rayas", "Falla en GPU", orders.get(1), Type.NOTEBOOK, State.RECIBIDO, null, null);
        Device device3 = new Device(null, "HP Pavilion 15", "SN-00003", "Cargador genérico", new BigDecimal(8000), new BigDecimal(80000), "No carga", "Batería agotada", orders.get(2), Type.NOTEBOOK, State.EN_DIAGNOSTICO, null, null);
        Device device4 = new Device(null, "Samsung Galaxy S21", "SN-00004", "Sin accesorios", new BigDecimal(9000), new BigDecimal(90000), "No enciende", "Falla en la placa", orders.get(3), Type.SMARTPHONE, State.EN_DIAGNOSTICO, null, null);
        Device device5 = new Device(null, "Dell Inspiron 5000", "SN-00005", "Teclado y Mouse", new BigDecimal(10000), new BigDecimal(100000), "No enciende", "Fuente dañada", orders.get(4), Type.COMPUTADORA_DE_ESCRITORIO, State.EN_REPARACION, null, null);
        Device device6 = new Device(null, "iPhone 13", "SN-00006", "Cargador", new BigDecimal(15000), new BigDecimal(150000), "Pantalla dañada", "Falla en la cámara", orders.get(5), Type.SMARTPHONE, State.EN_REPARACION, null, null);
        Device device7 = new Device(null, "Samsung Galaxy Tab S7", "SN-00007", "Cargador", new BigDecimal(15000), new BigDecimal(150000), "Pantalla rota", "Batería dañada", orders.get(6), Type.TABLET, State.LISTO_PARA_RETIRO, null, null);
        Device device8 = new Device(null, "Lenovo ThinkPad X1 Carbon", "SN-00008", "Mouse inalámbrico", new BigDecimal(50000), new BigDecimal(500000), "No carga", "Placa base dañada", orders.get(7), Type.NOTEBOOK, State.ENTREGADO, null, null);
        Device device9 = new Device(null, "MacBook Air 2020", "SN-00009", "Cargador original", new BigDecimal(100000), new BigDecimal(1000000), "Pantalla parpadeante", "Problema en la GPU", orders.get(8), Type.NOTEBOOK, State.RECIBIDO, null, null);
        Device device10 = new Device(null, "Sony Xperia 5", "SN-00010", "Cargador", new BigDecimal(7000), new BigDecimal(70000), "No enciende", "Falla en la placa base", orders.get(9), Type.SMARTPHONE, State.CANCELADO, null, null);

        // Guarda los dispositivos
        deviceRepository.saveAll(List.of(device1, device2, device3, device4, device5, device6, device7, device8, device9, device10));

        // Limpia y añade los dispositivos a las órdenes correspondientes
        for (int i = 0; i < 10; i++) {
            orders.get(i).getDevices().clear(); // Limpia cualquier dispositivo existente
        }

        orders.get(0).getDevices().add(device1);
        orders.get(1).getDevices().add(device2);
        orders.get(2).getDevices().add(device3);
        orders.get(3).getDevices().add(device4);
        orders.get(4).getDevices().add(device5);
        orders.get(5).getDevices().add(device6);
        orders.get(6).getDevices().add(device7);
        orders.get(7).getDevices().add(device8);
        orders.get(8).getDevices().add(device9);
        orders.get(9).getDevices().add(device10);

        // Actualiza los totales de las órdenes
        orders.forEach(Order::updateOrderTotal);

        // Guarda las órdenes actualizadas
        orderRepository.saveAll(orders);
    }
}
