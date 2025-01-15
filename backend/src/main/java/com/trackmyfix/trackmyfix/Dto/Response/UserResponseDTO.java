package com.trackmyfix.trackmyfix.Dto.Response;

import com.trackmyfix.trackmyfix.entity.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDTO {
    private Long id;
    private UserRole userRole;
}
