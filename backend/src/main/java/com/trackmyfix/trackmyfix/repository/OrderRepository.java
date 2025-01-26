package com.trackmyfix.trackmyfix.repository;

import ch.qos.logback.core.status.Status;
import com.trackmyfix.trackmyfix.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
    Optional<Order> findByNumber(String number);
    Optional<Order>findTopByOrderByIdOrderDesc();
}
