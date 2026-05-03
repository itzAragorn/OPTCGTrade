package com.example.optcgtrader.controller;

import com.example.optcgtrader.dto.request.ListingRequestDTO;
import com.example.optcgtrader.dto.response.ListingResponseDTO;
import com.example.optcgtrader.service.ListingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @GetMapping
    public List<ListingResponseDTO> getAll() {
        return listingService.getAll();
    }

    @GetMapping("/{id}")
    public ListingResponseDTO getById(@PathVariable Long id) {
        return listingService.getById(id);
    }

    @PostMapping
    public ListingResponseDTO create(@Valid @RequestBody ListingRequestDTO dto) {
        return listingService.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        listingService.delete(id);
    }

    @PutMapping("/{id}")
    public ListingResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody ListingRequestDTO dto
    ) {
        return listingService.update(id, dto);
    }
}