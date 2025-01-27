package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.User;

import java.util.Map;

public interface IUserService<T extends UserResponseDTO> {

    T findById(Long id);
    T save(UserRequestDTO user);
    T update(UserRequestDTO user);
    Map<String, String> delete(Long id);
}
