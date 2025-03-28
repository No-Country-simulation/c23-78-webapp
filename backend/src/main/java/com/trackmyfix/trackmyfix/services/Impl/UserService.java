package com.trackmyfix.trackmyfix.services.Impl;

import com.trackmyfix.trackmyfix.Dto.Request.TokenType;
import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.aspects.annotations.UserChangeNotify;
import com.trackmyfix.trackmyfix.configs.auth.JWTService;
import com.trackmyfix.trackmyfix.configs.auth.MyUserDetailsService;
import com.trackmyfix.trackmyfix.entity.ActionUser;
import com.trackmyfix.trackmyfix.entity.Role;
import com.trackmyfix.trackmyfix.entity.User;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import com.trackmyfix.trackmyfix.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleInfoNotFoundException;
import javax.naming.InsufficientResourcesException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static com.trackmyfix.trackmyfix.entity.Role.*;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private UserRepository<User> userRepository;
    private AdminService adminService;
    private TechnicianService technicianService;
    private ClientService clientService;

    private JWTService jwtService;
    AuthenticationManager authManager;
    private MyUserDetailsService myUserDetails;

    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));
        return switch (user.getRole()) {
            case ADMIN -> adminService.findById(id);
            case TECHNICIAN -> technicianService.findById(id);
            case CLIENT -> clientService.findById(id);
        };
    }

    @SneakyThrows
    @UserChangeNotify(actionUser = ActionUser.AGREGO_CLIENTE)
    public UserResponseDTO save(UserRequestDTO user) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            String authority = auth.getAuthorities().toArray()[0].toString();
            switch (user.getRole()) {
                case ADMIN -> {
                    if (Objects.equals(authority, ADMIN.name())) {
                        return adminService.save(user);
                    } else {
                        throw new InsufficientAuthenticationException("Only Admins can create "+ ADMIN.name());
                    }
                }
                case TECHNICIAN -> {
                    if (Objects.equals(authority, ADMIN.name())) {
                        return technicianService.save(user);
                    } else {
                        throw new InsufficientAuthenticationException("Only Admins can create "+TECHNICIAN.name());
                    }
                }
                case CLIENT -> {
                    return clientService.save(user);
                }
                default -> throw new RoleInfoNotFoundException("Role not found");
            }
        } else {
            throw new InsufficientAuthenticationException("you must be authenticated before create user");
        }
    }

    @SneakyThrows
    @UserChangeNotify(actionUser = ActionUser.MODIFICO_DATOS_CLIENTE)
    public UserResponseDTO update(UserRequestDTO user) {
        UserResponseDTO verify = this.findById(user.getId());
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            String authority = auth.getAuthorities().toArray()[0].toString();
            switch (user.getRole()) {
                case ADMIN -> {
                    if (Objects.equals(authority, ADMIN.name())) {
                        return adminService.save(user);
                    } else {
                        throw new InsufficientAuthenticationException("Only Admins can update "+ ADMIN.name());
                    }
                }
                case TECHNICIAN -> {
                    if (Objects.equals(authority, ADMIN.name())) {
                        return technicianService.save(user);
                    } else {
                        throw new InsufficientAuthenticationException("Only Admins can update "+TECHNICIAN.name());
                    }
                }
                case CLIENT -> {
                    if (verify.getRole() == CLIENT) {
                        return clientService.update(user);
                    } else if (Objects.equals(authority, ADMIN.name())) {
                            return clientService.update(user);
                    } else {
                        throw new InsufficientAuthenticationException("Only Admin can change user Role");
                    }
                }
                default -> throw new RoleInfoNotFoundException("role Not found");
            }
        } else {
            throw new InsufficientAuthenticationException("Only Admins can create "+TECHNICIAN.name());
        }
    }

    @SneakyThrows
    @UserChangeNotify(actionUser = ActionUser.DESACTIVO_CUENTA_CLIENTE)
    public Map<String,String> delete(Long id) {
        UserResponseDTO user = this.findById(id);
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            String authority = auth.getAuthorities().toArray()[0].toString();
            switch (user.getRole()) {
                case ADMIN -> {
                    if (Objects.equals(authority, ADMIN.name())) {
                        return adminService.delete(id);
                    } else {
                        throw new InsufficientAuthenticationException("Only Admins can Delete "+ ADMIN.name());
                    }
                }
                case TECHNICIAN -> {
                    if (Objects.equals(authority, ADMIN.name())) {
                        return technicianService.delete(id);
                    } else {
                        throw new InsufficientAuthenticationException("Only Admins can Delete "+ TECHNICIAN.name());
                    }
                }
                case CLIENT -> {
                    return clientService.delete(id);
                }
                default -> throw new RoleInfoNotFoundException("Role not found");
            }
        } else {
            throw new InsufficientAuthenticationException("authentication error");
        }
    }
    @SneakyThrows
    public Map<String, String> verify(String username, String password) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(username);
        } else {
            throw new Exception("Login failed");
        }
    }
    @SneakyThrows
    public Map<String, String> refreshToken(String token) {
        String email = jwtService.extractUsername(token, TokenType.REFRESH);
        UserDetails userDetails = myUserDetails.loadUserByUsername(email);
        boolean isValid = jwtService.validateToken(token,TokenType.REFRESH, userDetails);
        if (isValid) {
            return jwtService.generateToken(email);
        } else {
            throw new JwtException("Invalid refresh Token");
        }
    }

    public List<UserResponseDTO> findAll(Role role){
        return userRepository.findAll().stream()
                    .filter(user -> user.getRole() == role)
                    .map(this::mapToDTO).toList();
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
}
