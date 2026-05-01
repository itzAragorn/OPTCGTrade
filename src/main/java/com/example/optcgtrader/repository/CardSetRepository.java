package com.example.optcgtrader.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.optcgtrader.model.entity.CardSet;

public interface CardSetRepository extends JpaRepository<CardSet, Long> {
}
