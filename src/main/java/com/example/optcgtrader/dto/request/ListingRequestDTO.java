package com.example.optcgtrader.dto.request;

import com.example.optcgtrader.model.enums.CardCondition;
import com.example.optcgtrader.model.enums.CardLanguage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingRequestDTO {

    private Integer price;

    private Integer stock;

    private CardCondition condition;

    private CardLanguage language;

    private Long sellerId;

    private Long cardId;
}