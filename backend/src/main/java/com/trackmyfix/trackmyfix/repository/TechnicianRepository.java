package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.Technician;
import org.springframework.data.repository.CrudRepository;

public interface TechnicianRepository extends CrudRepository<Technician,Long> {
}
