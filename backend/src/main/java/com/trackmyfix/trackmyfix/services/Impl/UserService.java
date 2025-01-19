package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.IUserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class UserService implements IUserService<UserResponseDTO> {

    private final UserRepository<User> userRepository;
    private final UserRepository<Admin> adminRepository;
    private final UserRepository<Client> clientRepository;
    private final UserRepository<Technician> technicianRepository;


    @SneakyThrows
    public UserResponseDTO findById(Long id) {
        var user = this.userRepository.findByPk(id)
                .orElseThrow(()-> new UserNotFoundException("User_collection"));
        return mapToDTO(user);
    }

    @SneakyThrows
    @Transactional
    public UserResponseDTO save(UserRequestDTO user) {
        User savedUser = null;
        switch(user.getRole()) {
            case ADMIN -> {
                savedUser = this.adminRepository.save((Admin) mapToEntity(user));
            }
            case CLIENT -> {
                savedUser = this.clientRepository.save((Client) mapToEntity(user));
            }
            case TECHNICIAN -> {
                savedUser = this.technicianRepository.save((Technician) mapToEntity(user));
            }
            default -> throw new IllegalStateException("Unexpected value: " + user.getRole());
        }
        return mapToDTO(savedUser);
    }

    @Override
    public UserResponseDTO update(UserRequestDTO user) {
        this.findById(user.getId());
        return this.save(user);
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        this.userRepository.deleteById(id);
    }

    private <T extends User> Object mapToEntity(UserRequestDTO user) {
        Object userEntity = null;
        switch(user.getRole()) {
            case ADMIN -> {
                userEntity = Admin.builder()
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
            case CLIENT -> {
                userEntity = Client.builder()
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
            case TECHNICIAN -> {
                userEntity = Technician.builder()
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
        }
        return userEntity;
    }

    private UserResponseDTO mapToDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(user.getRole())
                .dni(user.getDni())
                .active(user.getActive())
                .build();
    }
    private UserResponseDTO mapToDTO(Admin user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(user.getRole())
                .dni(user.getDni())
                .active(user.getActive())
                .build();
    }
    private UserResponseDTO mapToDTO(Client user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(user.getRole())
                .dni(user.getDni())
                .active(user.getActive())
                .build();
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
                .dni(user.getDni())
                .active(user.getActive())
                .build();
    }

}
