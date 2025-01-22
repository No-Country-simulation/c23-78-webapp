package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.Technician;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TechnicianService implements IUserService<UserResponseDTO> {

    private final UserRepository<Technician> technicianRepository;
    private BCryptPasswordEncoder encoder;


    @Override
    public UserResponseDTO findById(Long id) {
        return mapToDTO(technicianRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("Technician "+id+" not found")));
    }

    @Override
    public UserResponseDTO save(UserRequestDTO user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return mapToDTO(technicianRepository.save(mapToEntity(user)));
    }

    @Override
    public UserResponseDTO update(UserRequestDTO user) {
        this.findById(user.getId());
        return this.save(user);
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        technicianRepository.deleteById(id);
    }

    private UserResponseDTO mapToDTO(Technician user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(user.getRole())
                .active(user.getActive())
                .dni(user.getDni())
                .build();
    }

    private Technician mapToEntity(UserRequestDTO user) {
        return Technician.builder()
                .id(user.getId() != null ? user.getId() : null)
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(user.getRole())
                .active(user.getActive())
                .dni(user.getDni())
                .build();
    }
}
