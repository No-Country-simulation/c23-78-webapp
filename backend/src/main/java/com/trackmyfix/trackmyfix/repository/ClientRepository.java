package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client,Long> {
    Optional<Client> findByDni(String dni);
}
