package com.trackmyfix.trackmyfix.event;

import com.trackmyfix.trackmyfix.Dto.Request.OrderRequest;
import com.trackmyfix.trackmyfix.entity.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DeviceEvent extends ApplicationEvent {
    private final OrderRequest order;

    public DeviceEvent(OrderRequest order) {
        super(order);
        this.order = order;
    }
}
