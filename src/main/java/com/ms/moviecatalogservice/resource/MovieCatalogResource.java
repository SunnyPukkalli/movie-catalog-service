package com.ms.moviecatalogservice.resource;


import com.ms.moviecatalogservice.model.CatalogItem;
import com.ms.moviecatalogservice.model.UserRating;
import com.ms.moviecatalogservice.service.MovieInfoService;
import com.ms.moviecatalogservice.service.UserRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog/")
public class MovieCatalogResource {

    Logger logger = LoggerFactory.getLogger(MovieCatalogResource.class);

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private UserRatingService userRatingService;

    @GetMapping("/users/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") int userId){

        logger.info("User Id Received in input is :"+userId);

        // Get Ratings for movies watched by this user
        logger.info("Calling Ratinds Data Service");
        UserRating userRating = userRatingService.getUserRating(userId);

        // Based on the Rating object get Movie Info and return data
        logger.info("Calling Movie Info Service");
        return userRating.getRatings().stream()
                .map(rating -> movieInfoService.getCatalogItem(rating))
                .collect(Collectors.toList());
    }
}