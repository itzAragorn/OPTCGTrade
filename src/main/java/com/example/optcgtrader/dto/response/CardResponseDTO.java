package com.example.optcgtrader.dto.response;

import com.example.optcgtrader.model.enums.Color;
import com.example.optcgtrader.model.enums.Rarity;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardResponseDTO {

    private Long id;

    private String name;

    private String code;

    private Set<Color> colors;

    private Rarity rarity;

    private CardSetResponseDTO set;

    private String imageUrl;
}