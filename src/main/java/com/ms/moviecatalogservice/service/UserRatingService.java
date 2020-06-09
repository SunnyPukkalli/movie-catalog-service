package com.ms.moviecatalogservice.service;

import com.ms.moviecatalogservice.model.UserRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRatingService {

    Logger logger = LoggerFactory.getLogger(UserRatingService.class);

    @Value("${ratings-data:default}")
    private String RATINGS_DATA_URL;

    @Autowired
    private RestTemplate restTemplate;

    public UserRating getUserRating(int userId) {

        String URL = getURL(userId);
        logger.info("Performing REST API Call to ratings-data to URL :- "+URL);
        return restTemplate.getForObject(URL, UserRating.class);
    }

    private String getURL(int userId) {
        return RATINGS_DATA_URL+userId;
    }
}
