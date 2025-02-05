package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.aspects.annotations.UserChangeNotify;
import com.trackmyfix.trackmyfix.entity.Client;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import com.trackmyfix.trackmyfix.services.IUserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class ClientService implements IUserService<UserResponseDTO> {
    private UserRepository<Client> clientRepository;
    private BCryptPasswordEncoder encoder;

    public List<UserResponseDTO> findAll() {
        return clientRepository.findAll().stream().map(this::mapToDTO).toList();
    }

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
    public Map<String,String> delete(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
        if (client.getActive()){
            clientRepository.deleteById(id);
            return Map.of("message","User id: "+id+" marked as INACTIVE success");
        } else {
            client.setActive(true);
            clientRepository.save(client);
            return Map.of("message","User id: "+id+" marked as ACTIVE success");
        }
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
