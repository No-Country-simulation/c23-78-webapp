package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    Optional<T> findByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.id = :id_user")
    Optional<T> findByPk(@Param("id_user") Long id_user);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.active = true")
    Optional<T> findByEmailAndActive(@Param("email") String email);
}
