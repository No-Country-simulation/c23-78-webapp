package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Response.UserChangeResponseDTO;
import com.trackmyfix.trackmyfix.entity.ActionUser;

import java.util.Set;

public interface IUserChangeService {

    Set<UserChangeResponseDTO> findAll();
    void save(ActionUser actionUser, Long technicianId, Long userId);
}
