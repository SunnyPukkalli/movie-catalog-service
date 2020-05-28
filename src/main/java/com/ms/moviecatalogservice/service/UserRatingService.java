package com.ms.moviecatalogservice.service;

import com.ms.moviecatalogservice.model.UserRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRatingService {

    Logger logger = LoggerFactory.getLogger(UserRatingService.class);

    @Autowired
    private RestTemplate restTemplate;

    public UserRating getUserRating(int userId) {

        logger.info("Performing REST API Call to ratings-data.");

        return restTemplate.getForObject("http://ratings-data/ratings/users/"+userId, UserRating.class);
    }
}
