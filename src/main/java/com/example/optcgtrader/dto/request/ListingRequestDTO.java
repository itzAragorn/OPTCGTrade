package com.example.optcgtrader.dto.request;

import com.example.optcgtrader.model.enums.CardCondition;
import com.example.optcgtrader.model.enums.CardLanguage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingRequestDTO {

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 1, message = "El precio debe ser mayor a 0")
    private Integer price;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 1, message = "El stock debe ser mayor a 0")
    private Integer stock;

    @NotNull(message = "La condición es obligatoria")
    private CardCondition condition;

    @NotNull(message = "El idioma es obligatorio")
    private CardLanguage language;

    @NotNull(message = "El sellerId es obligatorio")
    private Long sellerId;

    @NotNull(message = "El cardId es obligatorio")
    private Long cardId;
}