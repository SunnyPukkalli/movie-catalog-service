package com.ms.moviecatalogservice.service;

import com.ms.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRatingService {

    @Autowired
    private RestTemplate restTemplate;


    public UserRating getUserRating(@PathVariable("userId") String userId) {
        return restTemplate.getForObject("http://ratings-data-service/ratings/users/"+userId, UserRating.class);
    }
}
