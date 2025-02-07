package com.trackmyfix.trackmyfix.event;

import com.trackmyfix.trackmyfix.entity.Action;
import com.trackmyfix.trackmyfix.entity.Device;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DeviceStateChangeEvent extends ApplicationEvent {
    private final Device device;
    private final String description;
    private final Action action;

    public DeviceStateChangeEvent(Device device, Action action, String description) {
        super(device);
        this.device = device;
        this.description = description;
        this.action = action;
    }
}