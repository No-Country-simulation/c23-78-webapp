package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {
}
