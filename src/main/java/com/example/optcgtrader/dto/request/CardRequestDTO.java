package com.example.optcgtrader.dto.request;

import com.example.optcgtrader.model.enums.Color;
import com.example.optcgtrader.model.enums.Rarity;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardRequestDTO {

    private String name;

    private String code;

    private Set<Color> colors;

    private Rarity rarity;

    private Long setId;

    private String imageUrl;

}