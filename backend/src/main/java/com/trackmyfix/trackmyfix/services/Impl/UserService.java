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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class UserService implements IUserService<UserResponseDTO> {

    private UserRepository<User> userRepository;
    private UserRepository<Admin> adminRepository;
    private UserRepository<Client> clientRepository;
    private UserRepository<Technician> technicianRepository;


    @SneakyThrows
    public UserResponseDTO findById(Long id) {
        UserResponseDTO user = null;
        var client = clientRepository.findById(id);
        System.out.println(client.get().toString());
        if (client.isPresent()) {
            System.out.println(client.get().toString());
            user = mapToDTO(client.get());
        }
        var technician = technicianRepository.findById(id);
        if(technician.isPresent()) {
            user = mapToDTO(technician.get());
        }
        var admin = adminRepository.findById(id);
        if(admin.isPresent()) {
            user = mapToDTO(admin.get());
        }
        return user;
    }

    @SneakyThrows
    @Transactional
    public UserResponseDTO save(UserRequestDTO user) {
        switch(user.getRole()) {
            case ADMIN -> {
                return mapToDTO(adminRepository.save((Admin) mapToEntity(user)));
            }
            case CLIENT -> {
                return mapToDTO(clientRepository.save((Client) mapToEntity(user)));
            }
            case TECHNICIAN -> {
                return mapToDTO(technicianRepository.save((Technician) mapToEntity(user)));
            }
            default -> throw new IllegalStateException("Unexpected value: " + user.getRole());
        }
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
