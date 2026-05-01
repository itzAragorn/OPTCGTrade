package com.example.optcgtrader.service;

import com.example.optcgtrader.model.entity.Card;
import com.example.optcgtrader.model.entity.CardSet;
import com.example.optcgtrader.repository.CardRepository;
import com.example.optcgtrader.repository.CardSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardSetRepository cardSetRepository;

    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    public Card getById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carta no encontrada"));
    }

    public Card create(Card card) {

        Long setId = card.getSet().getId();

        CardSet set = cardSetRepository.findById(setId)
                .orElseThrow(() -> new RuntimeException("Set no encontrado"));

        card.setSet(set);

        return cardRepository.save(card);
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }
}