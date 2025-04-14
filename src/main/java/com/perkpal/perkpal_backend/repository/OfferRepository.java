package com.perkpal.perkpal_backend.repository;

import com.perkpal.perkpal_backend.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface OfferRepository extends MongoRepository<Offer, String> {
    // Method to find offers by category
    List<Offer> findByCategory(String category);

    // Method to find active offers
    List<Offer> findByActive(boolean active);

    // Method to find offers by target group (e.g., "students")
    List<Offer> findByTargetGroup(String targetGroup);
}
