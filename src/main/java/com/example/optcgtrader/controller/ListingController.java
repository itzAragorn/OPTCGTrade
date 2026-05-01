package com.example.optcgtrader.controller;

import com.example.optcgtrader.dto.request.ListingRequestDTO;
import com.example.optcgtrader.dto.response.ListingResponseDTO;
import com.example.optcgtrader.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService service;

    @GetMapping
    public List<ListingResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ListingResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ListingResponseDTO create(@RequestBody ListingRequestDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}