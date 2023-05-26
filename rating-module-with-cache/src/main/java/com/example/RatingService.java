package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RatingService {
    @Autowired
    RatingRepo ratingRepository;


    /*
    when the findRatingById fails or gets delayed more than a given threshold, Hystrix fallbacks to findCachedRatingById.
    */
    @HystrixCommand(
            commandKey = "findById",
            fallbackMethod = "findCachedRatingById"
    )
    public Optional<RatingEntity> findRatingById(Long ratingId) {
        log.info("From Method");
        return Optional.ofNullable(ratingRepository.findById(ratingId)
                .orElseThrow(() ->
                        new RuntimeException("Rating not found. ID: " + ratingId)));
    }

    public Optional<RatingEntity> findCachedRatingById(Long ratingId) {
        log.info("From Fallback Method");
        return Optional.ofNullable(new RatingEntity(404l,"fallback"));
    }
}
