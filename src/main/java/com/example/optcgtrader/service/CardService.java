package com.example.optcgtrader.service;

import com.example.optcgtrader.dto.request.CardRequestDTO;
import com.example.optcgtrader.dto.response.CardResponseDTO;
import com.example.optcgtrader.dto.response.CardSetResponseDTO;
import com.example.optcgtrader.exception.DuplicateResourceException;
import com.example.optcgtrader.exception.ResourceNotFoundException;
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

    public List<CardResponseDTO> getAll() {
        return cardRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public CardResponseDTO getById(Long id) {

        Card card = cardRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Carta no encontrada"));

        return mapToDTO(card);
    }

    public CardResponseDTO create(CardRequestDTO dto) {

        if (cardRepository.existsByCode(dto.getCode())) {
            throw new DuplicateResourceException("El código de carta ya existe");
        }

        CardSet set = cardSetRepository.findById(dto.getSetId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Set no encontrado"));

        Card card = Card.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .colors(dto.getColors())
                .rarity(dto.getRarity())
                .set(set)
                .imageUrl(dto.getImageUrl())
                .build();

        return mapToDTO(cardRepository.save(card));
    }

    public void delete(Long id) {

        if (!cardRepository.existsById(id)) {
            throw new ResourceNotFoundException("Carta no encontrada");
        }

        cardRepository.deleteById(id);
    }

    private CardResponseDTO mapToDTO(Card card) {

        return CardResponseDTO.builder()
                .id(card.getId())
                .name(card.getName())
                .code(card.getCode())
                .colors(card.getColors())
                .rarity(card.getRarity())
                .imageUrl(card.getImageUrl())
                .set(
                        CardSetResponseDTO.builder()
                                .id(card.getSet().getId())
                                .code(card.getSet().getCode())
                                .name(card.getSet().getName())
                                .build()
                )
                .build();
    }
}