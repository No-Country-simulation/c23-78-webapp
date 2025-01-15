package com.trackmyfix.trackmyfix.Dto.Request;

import com.trackmyfix.trackmyfix.entity.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequestDTO {
    private Long id;
    private UserRole userRole;
}
