package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.ActionUser;
import org.springframework.data.repository.CrudRepository;

public interface ActionUserRepository extends CrudRepository<ActionUser,Long> {
    ActionUser findByName(String name);
}
