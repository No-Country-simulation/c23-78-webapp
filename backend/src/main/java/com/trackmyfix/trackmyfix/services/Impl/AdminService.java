package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.ActionUser;
import com.trackmyfix.trackmyfix.entity.Admin;
import com.trackmyfix.trackmyfix.entity.Technician;
import com.trackmyfix.trackmyfix.entity.UserChange;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.UserChangeRepository;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.IUserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
@AllArgsConstructor
public class AdminService implements IUserService<UserResponseDTO> {

    private UserRepository<Admin> adminRepository;
    private BCryptPasswordEncoder encoder;
    private UserChangeRepository userChangeRepository;

    @Override
    public UserResponseDTO findById(Long id) {
        return mapToDTO(adminRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Admin " + id + " not found")));
    }

    @Override
    public UserResponseDTO save(UserRequestDTO user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return mapToDTO(adminRepository.save(mapToEntity(user)));
    }

    @Override
    public UserResponseDTO update(UserRequestDTO user) {
        this.findById(user.getId());
        return this.save(user);
    }

    @Override
    @SneakyThrows
    public Map<String,String> delete(Long id) {
        this.findById(id);
        adminRepository.deleteById(id);
        return Map.of("message","User id: "+id+" marked as inactive success");
    }

    private void createUserChange(ActionUser actionUser, Technician technician) {
        UserChange userChange = new UserChange();
        userChange.setActionUser(actionUser);
        userChange.setTechnician(technician);
        userChangeRepository.save(userChange);
    }

    private Admin mapToEntity(UserRequestDTO user) {
        return Admin.builder()
                .id(user.getId() != null ? user.getId() : null)
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(user.getRole())
                .active(user.getActive())
                .dni(user.getDni())
                .password(user.getPassword())
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
}
