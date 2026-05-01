package com.example.optcgtrader.service;

import com.example.optcgtrader.dto.request.ListingRequestDTO;
import com.example.optcgtrader.dto.response.CardSummaryDTO;
import com.example.optcgtrader.dto.response.ListingResponseDTO;
import com.example.optcgtrader.dto.response.UserSummaryDTO;
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

    public List<ListingResponseDTO> getAll() {
        return listingRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ListingResponseDTO getById(Long id) {

        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        return toResponseDTO(listing);
    }

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

        Listing saved = listingRepository.save(listing);

        return toResponseDTO(saved);
    }

    public void delete(Long id) {

        if (!listingRepository.existsById(id)) {
            throw new RuntimeException("Publicación no encontrada");
        }

        listingRepository.deleteById(id);
    }

    private ListingResponseDTO toResponseDTO(Listing listing) {

        return ListingResponseDTO.builder()
                .id(listing.getId())
                .price(listing.getPrice())
                .stock(listing.getStock())
                .condition(listing.getCondition())
                .language(listing.getLanguage())
                .seller(
                        UserSummaryDTO.builder()
                                .id(listing.getSeller().getId())
                                .username(listing.getSeller().getUsername())
                                .rating(listing.getSeller().getRating())
                                .build()
                )
                .card(
                        CardSummaryDTO.builder()
                                .id(listing.getCard().getId())
                                .name(listing.getCard().getName())
                                .code(listing.getCard().getCode())
                                .imageUrl(listing.getCard().getImageUrl())
                                .build()
                )
                .build();
    }
}