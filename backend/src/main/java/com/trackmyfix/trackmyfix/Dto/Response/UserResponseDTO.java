package com.trackmyfix.trackmyfix.Dto.Response;

import com.trackmyfix.trackmyfix.entity.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private String address;
    private String phone;
    private String email;
    private Boolean active;
    private Role role;
}
