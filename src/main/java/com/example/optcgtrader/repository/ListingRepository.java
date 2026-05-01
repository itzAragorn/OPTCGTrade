package com.example.optcgtrader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.optcgtrader.model.entity.Listing;

public interface ListingRepository extends JpaRepository<Listing, Long>{

}
