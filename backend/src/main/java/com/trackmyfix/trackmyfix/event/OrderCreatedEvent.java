package com.trackmyfix.trackmyfix.event;
import com.trackmyfix.trackmyfix.entity.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderCreatedEvent extends ApplicationEvent {

    private final Order order;

    public OrderCreatedEvent(Order order) {
        super(order);
        this.order = order;
    }
}
