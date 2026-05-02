package com.example.optcgtrader.service;

import com.example.optcgtrader.dto.request.UserRequestDTO;
import com.example.optcgtrader.dto.response.UserResponseDTO;
import com.example.optcgtrader.exception.DuplicateResourceException;
import com.example.optcgtrader.exception.ResourceNotFoundException;
import com.example.optcgtrader.model.entity.User;
import com.example.optcgtrader.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public UserResponseDTO getById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"));

        return mapToDTO(user);
    }

    public UserResponseDTO create(UserRequestDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("El email ya está registrado");
        }

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new DuplicateResourceException("El username ya está en uso");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .instagram(dto.getInstagram())
                .facebook(dto.getFacebook())
                .whatsapp(dto.getWhatsapp())
                .rating(0.0)
                .build();

        return mapToDTO(userRepository.save(user));
    }

    public void delete(Long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }

        userRepository.deleteById(id);
    }

    private UserResponseDTO mapToDTO(User user) {

        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .instagram(user.getInstagram())
                .facebook(user.getFacebook())
                .whatsapp(user.getWhatsapp())
                .rating(user.getRating())
                .build();
    }
}