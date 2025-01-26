package com.trackmyfix.trackmyfix.event;

import com.trackmyfix.trackmyfix.entity.Device;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class DeviceEvent extends ApplicationEvent {
    private final List<Device> devices;

    public DeviceEvent(List<Device> devices) {
        super(devices);
        this.devices = devices;
    }
}
