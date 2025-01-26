package com.trackmyfix.trackmyfix.Dto.Response;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserChangeResponseDTO {
    private Long id;
    private UserResponseDTO client;
    private UserResponseDTO userCommited;
    private String actionUser;
    private Date created_at;
}
