package com.example.optcgtrader.dto.request;

import com.example.optcgtrader.model.enums.Color;
import com.example.optcgtrader.model.enums.Rarity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El codigo es obligatorio")
    private String code;

    @NotEmpty(message = "Debete tener al menos un color")
    private Set<Color> colors;

    @NotNull(message = "La rareza es obligatoria")
    private Rarity rarity;

    @NotNull(message = "El id del set es obligatorio")
    private Long setId;

    private String imageUrl;

}