package com.example;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public class RatingController {
    @Autowired
    RatingService ratingService;
    @Autowired
    RatingRepo ratingRepository;
    @GetMapping("q/{id}")
    public Optional<RatingEntity> get(@PathVariable long id){
        log.info("Get By" +id);
        return ratingService.findRatingById(id);
    }
}
