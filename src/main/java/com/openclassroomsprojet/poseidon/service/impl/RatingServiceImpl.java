package com.openclassroomsprojet.poseidon.service.impl;

import com.openclassroomsprojet.poseidon.domain.Rating;
import com.openclassroomsprojet.poseidon.repositories.RatingRepository;
import com.openclassroomsprojet.poseidon.service.IRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RatingServiceImpl implements IRatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> findAllRating() {
        log.info("Call service method: findAllRating()");
        return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> findRatingById(int id) {
        log.info("Call service method: findRatingById(int id)");
        return ratingRepository.findById(id);
    }

    @Override
    public void deleteRatingById(int id) {
        log.info("Call service method: deleteRatingById(int id)");
        ratingRepository.deleteById(id);
    }

    @Override
    public void saveRating(Rating rating) {
        log.info("Call service method: saveRating(Rating rating)");
        ratingRepository.save(rating);
    }
}