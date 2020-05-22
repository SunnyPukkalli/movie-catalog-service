package com.ms.moviecatalogservice.service;

import com.ms.moviecatalogservice.model.Rating;
import com.ms.moviecatalogservice.model.UserRating;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingService {

    @Autowired
    private RestTemplate restTemplate;


//    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        return restTemplate.getForObject("http://ratings-data/ratings/users/"+userId, UserRating.class);
    }

/*
    private UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
        UserRating rating = new UserRating();
        rating.setRatings(
                Arrays.asList(
                        new Rating("0",0)
                )
        );

        return rating;
    }
*/

}
