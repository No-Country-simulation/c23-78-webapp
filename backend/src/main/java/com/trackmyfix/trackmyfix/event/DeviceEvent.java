package com.trackmyfix.trackmyfix.event;

import com.trackmyfix.trackmyfix.Dto.Request.DeviceRequestDTO;
import com.trackmyfix.trackmyfix.entity.Device;
import com.trackmyfix.trackmyfix.entity.Order;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class DeviceEvent extends ApplicationEvent {
    private final Order order;
    private final List<DeviceRequestDTO> devices;

    public DeviceEvent(Order order, List<DeviceRequestDTO> devices) {
        super(devices);
        this.devices = devices;
        this.order = order;
    }
}
