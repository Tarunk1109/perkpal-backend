package com.perkpal.perkpal_backend.service;

import com.perkpal.perkpal_backend.model.Offer;
import com.perkpal.perkpal_backend.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public List<Offer> getOffersByCategory(String category) {
        return offerRepository.findByCategory(category);
    }

    public List<Offer> getActiveOffers() {
        return offerRepository.findByActive(true);
    }

    public List<Offer> getOffersByTargetGroup(String targetGroup) {
        return offerRepository.findByTargetGroup(targetGroup);
    }

    public Offer addOrUpdateOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public List<Offer> getOffersForGroups(List<String> groups) {
        return offerRepository.findAll().stream()
                .filter(offer -> groups.contains(offer.getTargetGroup()))
                .filter(Offer::isActive) // Added active check
                .collect(Collectors.toList());
    }

    public List<Offer> getOffersForGroupsByCategory(List<String> groups, String category) {
        return offerRepository.findByCategory(category).stream()
                .filter(offer -> groups.contains(offer.getTargetGroup()))
                .filter(Offer::isActive)
                .collect(Collectors.toList());
    }
}