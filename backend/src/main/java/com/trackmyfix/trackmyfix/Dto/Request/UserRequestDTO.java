package com.trackmyfix.trackmyfix.Dto.Request;

import com.trackmyfix.trackmyfix.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequestDTO {

    private Long id;

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 50)
    private String name;

    @NotNull(message = "Last Name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "DNI is required")
    @Size(min = 7, max = 8, message = "DNI must be between 7 and 8 characters")
    private String dni;

    @NotNull(message = "Address is required")
    @Size(min = 3, max = 50, message = "Address must be between 3 and 50 characters")
    private String address;

    private String phone;
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;

    private Boolean active = true;

    @NotNull(message = "Role is required")
    private Role role = Role.CLIENT;

}
