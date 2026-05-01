package com.example.optcgtrader.model.entity;

import com.example.optcgtrader.model.enums.CardCondition;
import com.example.optcgtrader.model.enums.CardLanguage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "listings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;

    private Integer stock;

    @Enumerated(EnumType.STRING)
    private CardCondition condition;

    @Enumerated(EnumType.STRING)
    private CardLanguage language;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
}