package com.trackmyfix.trackmyfix.Dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequestDTO {
    @Email(message = "Username should be valid Email")
    private String username;

    @NotNull(message = "Password is required")
    private String password;
}
