package com.trackmyfix.trackmyfix.controller;

import com.trackmyfix.trackmyfix.Dto.Request.LoginRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Request.UserRequestDTO;
import com.trackmyfix.trackmyfix.Dto.Response.UserResponseDTO;
import com.trackmyfix.trackmyfix.entity.Role;
import com.trackmyfix.trackmyfix.entity.UserJwtData;
import com.trackmyfix.trackmyfix.services.Impl.UserService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String,String>> login(
                @RequestParam(name= "username") Optional<String> username,
                @RequestParam(name= "password") Optional<String> password,
                @RequestParam(name= "refresh_token") Optional<String> token
            ) {
        ResponseEntity<Map<String, String>> response = null;
        if (username.isPresent() && password.isPresent() && token.isEmpty()) {
            response = ResponseEntity.ok(userService.verify(username.get(), password.get()));
        }
        if (username.isEmpty() && password.isEmpty() && token.isPresent()) {
            response =  ResponseEntity.ok(userService.refreshToken(token.get()));
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> save(@Validated @RequestBody UserRequestDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseDTO> update(@Validated @RequestBody UserRequestDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.update(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> profile(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        UserJwtData user = (UserJwtData) authentication.getPrincipal();
        return ResponseEntity.ok(userService.findById(user.getId()));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> all(@RequestParam(name="role") Optional<Role> role){
        return ResponseEntity.ok(userService.findAll(role.orElse(Role.CLIENT)));
    }
}
