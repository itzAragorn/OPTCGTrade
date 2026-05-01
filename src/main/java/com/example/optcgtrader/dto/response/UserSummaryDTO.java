package com.example.optcgtrader.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSummaryDTO {

    private Long id;

    private String username;

    private Double rating;
}