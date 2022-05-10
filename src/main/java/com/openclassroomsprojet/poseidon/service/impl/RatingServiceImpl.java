package com.openclassroomsprojet.poseidon.service.impl;

import com.openclassroomsprojet.poseidon.domain.Rating;
import com.openclassroomsprojet.poseidon.repositories.RatingRepository;
import com.openclassroomsprojet.poseidon.service.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class RatingServiceImpl implements IRatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> findAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> findRatingById(int id) {
        return ratingRepository.findById(id);
    }

    @Override
    public void deleteRatingById(int id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }
}