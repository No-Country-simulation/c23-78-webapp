package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.Movement;
import org.springframework.data.repository.CrudRepository;

public interface MovementRepository extends CrudRepository<Movement , Long> {
}
