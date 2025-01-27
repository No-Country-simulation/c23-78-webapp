package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.aspects.annotations.UserChangeNotify;
import com.trackmyfix.trackmyfix.entity.Client;
import com.trackmyfix.trackmyfix.entity.Technician;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TechnicianService implements IUserService<UserResponseDTO> {

    private final UserRepository<Technician> technicianRepository;
    private BCryptPasswordEncoder encoder;

    public List<UserResponseDTO> findAll() {
        return technicianRepository.findAll().stream().map(this::mapToDTO).toList();
    }

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
    public Map<String,String> delete(Long id) {
        Technician technician = technicianRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
        if (technician.getActive()){
            technicianRepository.deleteById(id);
            return Map.of("message","User id: "+id+" marked as INACTIVE success");
        } else {
            technician.setActive(true);
            technicianRepository.save(technician);
            return Map.of("message","User id: "+id+" marked as ACTIVE success");
        }
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
