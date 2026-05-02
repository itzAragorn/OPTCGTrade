package com.example.optcgtrader.controller;

import com.example.optcgtrader.dto.request.CardRequestDTO;
import com.example.optcgtrader.dto.response.CardResponseDTO;
import com.example.optcgtrader.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public List<CardResponseDTO> getAll() {
        return cardService.getAll();
    }

    @GetMapping("/{id}")
    public CardResponseDTO getById(@PathVariable Long id) {
        return cardService.getById(id);
    }

    @PostMapping
    public CardResponseDTO create(@RequestBody CardRequestDTO dto) {
        return cardService.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cardService.delete(id);
    }
}