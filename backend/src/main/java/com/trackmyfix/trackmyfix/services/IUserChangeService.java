package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Response.UserChangeResponseDTO;

import java.util.Set;

public interface IUserChangeService {

    Set<UserChangeResponseDTO> findAll();
    void save(String actionName, Long technicianId, Long userId);
}
