package com.example.optcgtrader.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.optcgtrader.model.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
    
    Optional<Card> findByCode(String code);

    List<Card> findByNameContainingIgnoreCase(String name);
}
