package com.trackmyfix.trackmyfix.event;

import com.trackmyfix.trackmyfix.entity.Action;
import com.trackmyfix.trackmyfix.entity.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.Map;

@Getter
public class OrderCreateEvent extends ApplicationEvent {
    private final Order order;
    private final Action action;
    private  final Map<String, Object> changes;

    // Constructor para creación (sin cambios)
    public OrderCreateEvent(Order order, Action action) {
        super(order);
        this.order = order;
        this.action = action;
        this.changes = new HashMap<>();
    }

}
