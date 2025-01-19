package com.trackmyfix.trackmyfix.services;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;

public interface IUserService<T extends UserResponseDTO> {

    T findById(Long id);
    T save(UserRequestDTO user);
    T update(UserRequestDTO user);
    void delete(Long id);
}
