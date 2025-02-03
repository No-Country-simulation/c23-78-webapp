package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Response.UserChangeResponseDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.*;
import com.trackmyfix.trackmyfix.repository.UserChangeRepository;
import com.trackmyfix.trackmyfix.services.IUserChangeService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserChangeService implements IUserChangeService {

    private final UserChangeRepository userChangeRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public Set<UserChangeResponseDTO> findAll() {
        List<UserChange> userChanges =  userChangeRepository.findAll();
        return userChanges.stream().map(this::mapToDTO)
                .sorted((a,b) -> Math.toIntExact(a.getId() - b.getId()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public void save(ActionUser actionUser, Long technicianId, Long userId) {
        userChangeRepository.saveCustom(
                actionUser.name(),
                technicianId,
                userId
        );
    }

    @Override
    public Set<UserChangeResponseDTO> findByClientId(Long clientId) {
        List<UserChange> userChanges =  userChangeRepository.findByClientId(clientId);
        return userChanges.stream().map(this::mapToDTO)
                .sorted((a,b) -> Math.toIntExact(a.getId() - b.getId()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private UserChangeResponseDTO mapToDTO(UserChange userChange) {
        Technician tech = userChange.getTechnician();
        return UserChangeResponseDTO.builder()
                .id(userChange.getIdUserChange())
                .client(mapToDTO(userChange.getClient()))
                .technician(mapToDTO(tech))
                .actionUser(userChange.getActionUser().name())
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
