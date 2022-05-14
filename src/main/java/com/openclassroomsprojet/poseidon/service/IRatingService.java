package com.openclassroomsprojet.poseidon.service;

import com.openclassroomsprojet.poseidon.domain.Rating;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

public interface IRatingService {

    List<Rating> findAllRating();

    Optional<Rating> findRatingById(int id);

    void deleteRatingById(int id);

    void saveRating(Rating rating);
}