package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.Client;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.IUserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService implements IUserService<UserResponseDTO> {

    private UserRepository<Client> clientRepository;
    private BCryptPasswordEncoder encoder;


    @SneakyThrows
    @Override
    public UserResponseDTO findById(Long id) {
        return mapToDTO(clientRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("Client "+id+" not found")));
    }

    @Override
    public UserResponseDTO save(UserRequestDTO user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return mapToDTO(clientRepository.save(mapToEntity(user)));
    }

    @Override
    public UserResponseDTO update(UserRequestDTO user) {
        this.findById(user.getId());
        return this.save(user);
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        clientRepository.deleteById(id);
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
                .active(user.getActive())
                .dni(user.getDni())
                .build();
    }

    private Client mapToEntity(UserRequestDTO user) {
        return Client.builder()
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
