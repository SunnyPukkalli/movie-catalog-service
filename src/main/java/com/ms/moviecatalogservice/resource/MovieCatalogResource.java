package com.ms.moviecatalogservice.resource;


import com.ms.moviecatalogservice.model.CatalogItem;
import com.ms.moviecatalogservice.model.UserRating;
import com.ms.moviecatalogservice.service.MovieInfoService;
import com.ms.moviecatalogservice.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog/")
public class MovieCatalogResource {


    @Autowired
    MovieInfoService movieInfoService;

    @Autowired
    UserRatingService userRatingService;

    @RequestMapping("{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        UserRating userRating = userRatingService.getUserRating(userId);

        return userRating.getRatings().stream()
                .map(rating -> movieInfoService.getCatalogItem(rating))
                .collect(Collectors.toList());
    }
}