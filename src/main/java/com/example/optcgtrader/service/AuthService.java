package com.example.optcgtrader.service;

import com.example.optcgtrader.dto.request.LoginRequestDTO;
import com.example.optcgtrader.dto.request.UserRequestDTO;
import com.example.optcgtrader.dto.response.AuthResponseDTO;
import com.example.optcgtrader.dto.response.UserResponseDTO;
import com.example.optcgtrader.exception.ResourceNotFoundException;
import com.example.optcgtrader.model.entity.User;
import com.example.optcgtrader.repository.UserRepository;
import com.example.optcgtrader.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO register(UserRequestDTO dto) {

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userService.create(dto);
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        boolean matches = passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(user.getEmail());

        return AuthResponseDTO.builder()
                .token(token)
                .build();
    }
}