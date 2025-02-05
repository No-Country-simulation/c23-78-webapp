package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.Action;
import com.trackmyfix.trackmyfix.entity.Movement;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MovementRepository extends CrudRepository<Movement , Long> {
    List<Movement> findByAction(Action action);
}
