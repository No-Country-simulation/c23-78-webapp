package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
