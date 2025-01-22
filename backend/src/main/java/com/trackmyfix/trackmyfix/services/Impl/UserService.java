package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Request.LoginRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.Admin;
import com.trackmyfix.trackmyfix.entity.User;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository<User> userRepository;
    private final AdminService adminService;
    private final TechnicianService technicianService;
    private final ClientService clientService;

    private JWTService jwtService;
    AuthenticationManager authManager;

    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));
        return switch (user.getRole()) {
            case ADMIN -> adminService.findById(id);
            case TECHNICIAN -> technicianService.findById(id);
            case CLIENT -> clientService.findById(id);
        };
    }

    public UserResponseDTO save(UserRequestDTO user) {
        return switch (user.getRole()) {
            case ADMIN -> adminService.save(user);
            case TECHNICIAN -> technicianService.save(user);
            case CLIENT -> clientService.save(user);
        };
    }
    public UserResponseDTO update(UserRequestDTO user) {
        return switch (user.getRole()) {
            case ADMIN -> adminService.update(user);
            case TECHNICIAN -> technicianService.update(user);
            case CLIENT -> clientService.update(user);
        };
    }
    public void delete(Long id) {
        UserResponseDTO user = this.findById(id);
        switch (user.getRole()) {
            case ADMIN -> adminService.delete(id);
            case TECHNICIAN -> technicianService.delete(id);
            case CLIENT -> clientService.delete(id);
        }
    }
    @SneakyThrows
    public Map<String, String> verify(LoginRequestDTO user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            throw new Exception("Login failed");
        }
    }
}
