package com.example.optcgtrader.controller;

import com.example.optcgtrader.model.entity.Card;
import com.example.optcgtrader.service.CardService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService service;

    @GetMapping
    public List<Card> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Card getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Card create(@RequestBody Card card) {
        return service.create(card);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}