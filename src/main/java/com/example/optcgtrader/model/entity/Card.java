package com.example.optcgtrader.model.entity;

import java.util.Set;

import com.example.optcgtrader.model.enums.Color;
import com.example.optcgtrader.model.enums.Rarity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la carta
    @Column(nullable = false)
    private String name;

    // Código oficial (ej: OP01-001)
    @Column(nullable = false, unique = true)
    private String code;

    // Color (enum)
    @ElementCollection(targetClass = Color.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "card_colors", joinColumns = @JoinColumn(name = "card_id"))
    @Column(name = "color")
    private Set<Color> colors;

    // Rareza
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rarity rarity;
   
    // Set (relación)
    @ManyToOne
    @JoinColumn(name = "set_id", nullable = false)
    private CardSet set;

    // Opcional (futuro)
    private String imageUrl;
}
