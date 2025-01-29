package com.trackmyfix.trackmyfix.event;

import com.trackmyfix.trackmyfix.entity.Action;
import com.trackmyfix.trackmyfix.entity.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.Map;
@Getter
public class OrderUpdateEvent extends ApplicationEvent {
    private final Order order;
    private final Action action;
    private  final Map<String, Object> changes;

    // Constructor para creación (sin cambios)
    public OrderUpdateEvent(Order order, Action action) {
        super(order);
        this.order = order;
        this.action = action;
        this.changes = new HashMap<>();
    }

    // Constructor para actualización (con cambios)
    public OrderUpdateEvent(Order order, Action action, Map<String, Object> changes) {
        super(order);
        this.order = order;
        this.action = action;
        this.changes = changes != null? changes:new HashMap<>();
    }
}
