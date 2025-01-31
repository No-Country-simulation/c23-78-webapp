package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.entity.Device;
import com.trackmyfix.trackmyfix.entity.Order;
import com.trackmyfix.trackmyfix.entity.State;
import com.trackmyfix.trackmyfix.entity.Type;
import com.trackmyfix.trackmyfix.repository.DeviceRepository;
import com.trackmyfix.trackmyfix.repository.OrderRepository;
import com.trackmyfix.trackmyfix.repository.StateRepository;
import com.trackmyfix.trackmyfix.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DeviceDataLoader {
    private final DeviceRepository deviceRepository;
    private final OrderRepository orderRepository;
    private final TypeRepository typeRepository;
    private final StateRepository stateRepository;

    public void load() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        List<Type> types = (List<Type>) typeRepository.findAll();
        List<State> states = (List<State>) stateRepository.findAll();

        Device device1 = new Device(null, "Model1", "SN-00001", "Accessories1", "Description1", orders.get(0), types.get(0), states.get(0), null, null);
        Device device2 = new Device(null, "Model2", "SN-00002", "Accessories2", "Description2", orders.get(1), types.get(1), states.get(1), null, null);
        Device device3 = new Device(null, "Model3", "SN-00003", "Accessories3", "Description3", orders.get(2), types.get(2), states.get(2), null, null);
        Device device4 = new Device(null, "Model4", "SN-00004", "Accessories4", "Description4", orders.get(3), types.get(3), states.get(3), null, null);
        Device device5 = new Device(null, "Model5", "SN-00005", "Accessories5", "Description5", orders.get(4), types.get(4), states.get(4), null, null);
        Device device6 = new Device(null, "Model6", "SN-00006", "Accessories6", "Description6", orders.get(5), types.get(0), states.get(0), null, null);
        Device device7 = new Device(null, "Model7", "SN-00007", "Accessories7", "Description7", orders.get(6), types.get(1), states.get(1), null, null);
        Device device8 = new Device(null, "Model8", "SN-00008", "Accessories8", "Description8", orders.get(7), types.get(2), states.get(2), null, null);
        Device device9 = new Device(null, "Model9", "SN-00009", "Accessories9", "Description9", orders.get(8), types.get(3), states.get(3), null, null);
        Device device10 = new Device(null, "Model10", "SN-00010", "Accessories10", "Description10", orders.get(9), types.get(4), states.get(4), null, null);
        Device device11 = new Device(null, "Model11", "SN-00011", "Accessories11", "Description11", orders.get(0), types.get(0), states.get(0), null, null);
        Device device12 = new Device(null, "Model12", "SN-00012", "Accessories12", "Description12", orders.get(1), types.get(1), states.get(1), null, null);
        Device device13 = new Device(null, "Model13", "SN-00013", "Accessories13", "Description13", orders.get(2), types.get(2), states.get(2), null, null);
        Device device14 = new Device(null, "Model14", "SN-00014", "Accessories14", "Description14", orders.get(3), types.get(3), states.get(3), null, null);
        Device device15 = new Device(null, "Model15", "SN-00015", "Accessories15", "Description15", orders.get(4), types.get(4), states.get(4), null, null);

        deviceRepository.saveAll(List.of(device1, device2, device3, device4, device5, device6, device7, device8, device9, device10, device11, device12, device13, device14, device15));
    }
}
