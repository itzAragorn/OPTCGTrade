package com.example.optcgtrader.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CardSummaryDTO {

    private Long id;

    private String name;

    private String code;

    private String imageUrl;
}