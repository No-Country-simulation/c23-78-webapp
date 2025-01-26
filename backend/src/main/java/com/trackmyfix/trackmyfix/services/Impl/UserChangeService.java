package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Response.UserChangeResponseDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.ActionUserRepository;
import com.trackmyfix.trackmyfix.repository.UserChangeRepository;
import com.trackmyfix.trackmyfix.services.IUserChangeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserChangeService implements IUserChangeService {

    private final UserChangeRepository userChangeRepository;
    private final ActionUserRepository actionUserRepository;

    @Override
    public Set<UserChangeResponseDTO> findAll() {
        return userChangeRepository.findAll().stream().map(this::mapToDTO)
                .sorted((a,b) -> Math.toIntExact(a.getId() - b.getId()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public void save(String actionName, Long technicianId, Long userId) {
        ActionUser action = actionUserRepository.findByName(actionName);
        userChangeRepository.saveCustom(
                action.getIdActionUser(),
                technicianId,
                userId
        );
    }

    private UserChangeResponseDTO mapToDTO(UserChange userChange) {
        Technician tech = userChange.getTechnician();
        return UserChangeResponseDTO.builder()
                .id(userChange.getIdUserChange())
                .client(mapToDTO(userChange.getClient()))
                .userCommited(mapToDTO(tech))
                .actionUser(userChange.getActionUser().getName())
                .created_at(userChange.getCreatedAt())
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
                .active(user.getActive())
                .dni(user.getDni())
                .build();
    }
}
