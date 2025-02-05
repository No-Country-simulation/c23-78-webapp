package com.trackmyfix.trackmyfix.controller;

import com.trackmyfix.trackmyfix.Dto.Response.UserChangeResponseDTO;
import com.trackmyfix.trackmyfix.entity.UserChange;
import com.trackmyfix.trackmyfix.services.Impl.UserChangeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/logs")
@AllArgsConstructor
public class LogsController {

    private UserChangeService userChangeService;

    @GetMapping("/userchanges")
    private ResponseEntity<Set<UserChangeResponseDTO>> findAll(){
        return ResponseEntity.ok(userChangeService.findAll());
    }
}
