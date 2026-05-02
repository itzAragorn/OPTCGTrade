package com.example.optcgtrader.dto.request;

import com.example.optcgtrader.model.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    private String username;

    private String email;

    private String password;

    private Role role;

    private String instagram;

    private String facebook;

    private String whatsapp;
}