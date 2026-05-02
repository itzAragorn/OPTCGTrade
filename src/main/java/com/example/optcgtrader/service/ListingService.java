package com.example.optcgtrader.service;

import com.example.optcgtrader.dto.request.ListingRequestDTO;
import com.example.optcgtrader.dto.response.*;
import com.example.optcgtrader.model.entity.Card;
import com.example.optcgtrader.model.entity.Listing;
import com.example.optcgtrader.model.entity.User;
import com.example.optcgtrader.repository.CardRepository;
import com.example.optcgtrader.repository.ListingRepository;
import com.example.optcgtrader.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    // =========================
    // QUERIES
    // =========================

    public List<ListingResponseDTO> getAll() {
        return listingRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ListingResponseDTO getById(Long id) {

        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing no encontrado"));

        return toDTO(listing);
    }

    // =========================
    // CREATE
    // =========================

    public ListingResponseDTO create(ListingRequestDTO dto) {

        User seller = userRepository.findById(dto.getSellerId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Card card = cardRepository.findById(dto.getCardId())
                .orElseThrow(() -> new RuntimeException("Carta no encontrada"));

        Listing listing = Listing.builder()
                .price(dto.getPrice())
                .stock(dto.getStock())
                .condition(dto.getCondition())
                .language(dto.getLanguage())
                .seller(seller)
                .card(card)
                .build();

        return toDTO(listingRepository.save(listing));
    }

    // =========================
    // DELETE
    // =========================

    public void delete(Long id) {

        if (!listingRepository.existsById(id)) {
            throw new RuntimeException("Listing no encontrado");
        }

        listingRepository.deleteById(id);
    }

    // =========================
    // MAPPERS
    // =========================

    private ListingResponseDTO toDTO(Listing listing) {

        return ListingResponseDTO.builder()
                .id(listing.getId())
                .price(listing.getPrice())
                .stock(listing.getStock())
                .condition(listing.getCondition())
                .language(listing.getLanguage())

                .seller(toSellerDTO(listing.getSeller()))
                .card(toCardDTO(listing.getCard()))

                .build();
    }

    // SOLO INFO RESUMIDA DEL SELLER
    private UserSummaryDTO toSellerDTO(User user) {

        return UserSummaryDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .rating(user.getRating())
                .build();
    }

    // CARD RESUMIDA PARA MARKETPLACE
        private CardSummaryDTO toCardDTO(Card card) {

        return CardSummaryDTO.builder()
                    .id(card.getId())
                    .name(card.getName())
                    .code(card.getCode())
                    .rarity(card.getRarity()) // 👈 ENUM directo
                    .imageUrl(card.getImageUrl())
                    .build();
        }
}