package com.perkpal.perkpal_backend.controller;

import com.perkpal.perkpal_backend.model.Offer;
import com.perkpal.perkpal_backend.model.User;
import com.perkpal.perkpal_backend.service.OfferService;
import com.perkpal.perkpal_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/offers")
@CrossOrigin(origins = "http://localhost:5501") // Updated to match welcomepage.html
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/category/{category}")
    public List<Offer> getOffersByCategory(@PathVariable String category) {
        return offerService.getOffersByCategory(category);
    }

    @GetMapping("/active")
    public List<Offer> getActiveOffers() {
        return offerService.getActiveOffers();
    }

    @GetMapping("/target-group/{targetGroup}")
    public List<Offer> getOffersByTargetGroup(@PathVariable String targetGroup) {
        return offerService.getOffersByTargetGroup(targetGroup);
    }

    @PostMapping("/")
    public Offer addOrUpdateOffer(@RequestBody Offer offer) {
        return offerService.addOrUpdateOffer(offer);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Offer>> getOffersForUser(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            List<String> userGroups = user.get().getUserGroups();
            List<Offer> offers = offerService.getOffersForGroups(userGroups);
            return ResponseEntity.ok(offers);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/user/{email}/category/{category}")
    public ResponseEntity<List<Offer>> getOffersForUserByCategory(@PathVariable String email, @PathVariable String category) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            List<String> userGroups = user.get().getUserGroups();
            List<Offer> offers = offerService.getOffersForGroupsByCategory(userGroups, category);
            return ResponseEntity.ok(offers);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }
}