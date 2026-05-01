package com.example.optcgtrader.dto.response;

import com.example.optcgtrader.model.enums.CardCondition;
import com.example.optcgtrader.model.enums.CardLanguage;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListingResponseDTO {

    private Long id;

    private Integer price;

    private Integer stock;

    private CardCondition condition;

    private CardLanguage language;

    private UserSummaryDTO seller;

    private CardSummaryDTO card;
}