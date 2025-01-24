package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.DeviceRepository;
import com.trackmyfix.trackmyfix.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DeviceDataLoader {
    private final DeviceRepository deviceRepository;
    private final OrderRepository orderRepository;

    public void load() {
        List<Order> orders = (List<Order>) orderRepository.findAll();

        Device device1 = new Device(null, "Dell OptiPlex 3080", "SN-00001", "Teclado y Mouse", "No enciende", "Fuente dañada, necesita reemplazo", orders.get(0), Type.COMPUTADORA_DE_ESCRITORIO, State.RECIBIDO, null, null);
        Device device2 = new Device(null, "MacBook Pro 2020", "SN-00002", "Cargador original", "Pantalla con rayas", "Falla en GPU, posible reparación", orders.get(1), Type.NOTEBOOK, State.RECIBIDO, null, null);
        Device device3 = new Device(null, "HP Pavilion x360", "SN-00003", "Cargador y funda", "No carga batería", "Batería agotada, requiere reemplazo", orders.get(2), Type.NOTEBOOK, State.EN_DIAGNOSTICO, null, null);
        Device device4 = new Device(null, "Samsung Galaxy S21", "SN-00004", "Cable USB y cargador", "Pantalla rota", "Pantalla LCD dañada, requiere cambio", orders.get(3), Type.SMARTPHONE, State.EN_DIAGNOSTICO, null, null);
        Device device5 = new Device(null, "iPad Air 4", "SN-00005", "Smart Keyboard", "No responde el touch", "Conector táctil dañado", orders.get(4), Type.TABLET, State.EN_DIAGNOSTICO, null, null);
        Device device6 = new Device(null, "Lenovo ThinkPad T14", "SN-00006", "Docking station", "Sobrecalentamiento", "Revisión del sistema de ventilación", orders.get(5), Type.NOTEBOOK, State.RECIBIDO, null, null);
        Device device7 = new Device(null, "Acer Aspire 5", "SN-00007", "Cargador alternativo", "No arranca", "Posible fallo en BIOS", orders.get(6), Type.NOTEBOOK, State.EN_DIAGNOSTICO, null, null);
        Device device8 = new Device(null, "Asus ROG Strix", "SN-00008", "Cargador gaming", "Reinicio aleatorio", "Falla en módulo RAM", orders.get(7), Type.NOTEBOOK, State.REPARADO, null, null);
        Device device9 = new Device(null, "Xiaomi Redmi Note 11", "SN-00009", "Cable y auriculares", "Altavoz no funciona", "Falla en el flex de audio", orders.get(8), Type.SMARTPHONE, State.EN_PRUEBAS, null, null);
        Device device10 = new Device(null, "Microsoft Surface Pro 7", "SN-00010", "Cargador magnético", "Pantalla táctil descalibrada", "Requiere recalibración", orders.get(9), Type.TABLET, State.REPARADO, null, null);
        Device device11 = new Device(null, "iMac 27 2019", "SN-00011", "Teclado y mouse inalámbricos", "Lento al iniciar", "Cambio de disco a SSD recomendado", orders.get(0), Type.COMPUTADORA_DE_ESCRITORIO, State.RECIBIDO, null, null);
        Device device12 = new Device(null, "Dell XPS 13", "SN-00012", "Cargador original", "No enciende después de actualización", "Fallo en BIOS, posible reprogramación", orders.get(1), Type.NOTEBOOK, State.ENTREGADO, null, null);
        Device device13 = new Device(null, "HP EliteBook 840", "SN-00013", "Docking y cargador", "Teclado no responde", "Cambio de teclado requerido", orders.get(2), Type.NOTEBOOK, State.REPARADO, null, null);
        Device device14 = new Device(null, "Samsung Galaxy Tab S7", "SN-00014", "S Pen incluido", "No enciende", "Batería agotada, requiere cambio", orders.get(3), Type.TABLET, State.ENTREGADO, null, null);
        Device device15 = new Device(null, "Lenovo Yoga 9i", "SN-00015", "Lápiz digital y cargador", "Problema con la bisagra", "Mecanismo de bisagra dañado, requiere reemplazo", orders.get(4), Type.NOTEBOOK, State.ENTREGADO, null, null);

        deviceRepository.saveAll(List.of(device1, device2, device3, device4, device5, device6, device7, device8, device9, device10, device11, device12, device13, device14, device15));
    }

}
