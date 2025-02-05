package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.Client;
import com.trackmyfix.trackmyfix.entity.Technician;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TechnicianRepository extends CrudRepository<Technician,Long> {
    Optional<Technician> findByEmail(String email);
}
