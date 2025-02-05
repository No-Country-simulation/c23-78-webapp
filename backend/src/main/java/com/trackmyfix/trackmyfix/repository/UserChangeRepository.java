package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.ActionUser;
import com.trackmyfix.trackmyfix.entity.UserChange;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserChangeRepository extends CrudRepository<UserChange,Long> {

    List<UserChange> findAll();

    @Modifying
    @Query(value = "INSERT INTO user_change (action_user, id_technician, id_client) VALUES (:actionUser, :technicianId, :clientId)", nativeQuery = true)
    @Transactional
    void saveCustom(@Param("actionUser") String actionUser, @Param("technicianId") Long technicianId, @Param("clientId") Long clientId);
}
