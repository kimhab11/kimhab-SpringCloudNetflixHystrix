package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@Slf4j
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
public class RatingServiceApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(RatingServiceApp.class, args);
    }

    @Autowired
    RatingRepo ratingRepository;

    @Override
    public void run(String... args) throws Exception {
        ratingRepository.saveAndFlush(new RatingEntity(null,"AA"));
        ratingRepository.saveAndFlush(new RatingEntity(null,"BB"));
        ratingRepository.saveAndFlush(new RatingEntity(null,"CC"));
        log.info("Save All = {}",ratingRepository.count());
    }
}