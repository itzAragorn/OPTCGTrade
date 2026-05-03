package com.example.optcgtrader.dto.request;

import com.example.optcgtrader.model.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    @NotBlank(message = "El username es obligatorio")
    @Size(min = 3, max = 20)
    private String username;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, message = "La contraseña debe tener mínimo 4 caracteres")
    private String password;

    private Role role;

    private String instagram;

    private String facebook;

    private String whatsapp;
}