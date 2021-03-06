package com.liquorexchange.controllers;

import com.liquorexchange.db.model.AuctionView;
import com.liquorexchange.db.repository.AuctionRepository;
import com.liquorexchange.db.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/views")
public class ViewsController {

    @Autowired
    private AuctionRepository auction_repository;

    @Autowired
    private CountryRepository country_repository;

    @GetMapping("/auctions")
    public List<AuctionView> auctions() {
        return auction_repository.findAll().stream().map(auction -> new AuctionView(auction, country_repository)).collect(Collectors.toList());
    }

    @GetMapping("/auctions/byCountryCode")
    public List<AuctionView> auctionsByCountryCode(@RequestParam("countryCode") String code) {
        return auction_repository.findByCountryCode(code).stream().map(auction -> new AuctionView(auction, country_repository)).collect(Collectors.toList());
    }

}