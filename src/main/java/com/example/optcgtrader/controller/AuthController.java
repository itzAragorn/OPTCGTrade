package com.example.optcgtrader.controller;

import com.example.optcgtrader.dto.request.LoginRequestDTO;
import com.example.optcgtrader.dto.request.UserRequestDTO;
import com.example.optcgtrader.dto.response.AuthResponseDTO;
import com.example.optcgtrader.dto.response.UserResponseDTO;
import com.example.optcgtrader.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public UserResponseDTO register(
            @Valid @RequestBody UserRequestDTO dto
    ) {
        return service.register(dto);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(
            @RequestBody LoginRequestDTO dto
    ) {
        return service.login(dto);
    }
}