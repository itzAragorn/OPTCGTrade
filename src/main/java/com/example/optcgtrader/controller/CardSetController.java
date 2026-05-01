package com.example.optcgtrader.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.optcgtrader.model.entity.CardSet;
import com.example.optcgtrader.repository.CardSetRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/card-sets")
@RequiredArgsConstructor
public class CardSetController {
    
    private final CardSetRepository cardSetRepository;

    @PostMapping
    public CardSet create(@RequestBody CardSet set) {
        return cardSetRepository.save(set);
    }

    @GetMapping
    public List<CardSet> getAll() {
        return cardSetRepository.findAll();
    }
}