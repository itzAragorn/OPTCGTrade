package com.example.optcgtrader.dto.response;

import com.example.optcgtrader.model.enums.Role;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;

    private String username;

    private String email;

    private Role role;

    private LocalDateTime createdAt;

    private String instagram;

    private String facebook;

    private String whatsapp;

    private Double rating;

}