package com.ms.moviecatalogservice.resource;


import com.ms.moviecatalogservice.model.CatalogItem;
import com.ms.moviecatalogservice.model.Movie;
import com.ms.moviecatalogservice.model.Rating;
import com.ms.moviecatalogservice.model.UserRating;
import com.ms.moviecatalogservice.service.MovieInfoService;
import com.ms.moviecatalogservice.service.UserRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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


/*
    @Autowired
    private RestTemplate restTemplate;

    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId){
        return Arrays.asList(new CatalogItem("No Movie","",0));
    }

    @RequestMapping("{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        // Get all rated movie ID's
        List<Rating> ratings = Arrays.asList(
                new Rating("1",1),
                new Rating("2",2),
                new Rating("3",3)
        );

        // Get movie details for each movie ID
        return ratings.stream().map(rating -> {
           Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(),movie.getDesc(),rating.getRating());
        })
        .collect(Collectors.toList());

        // Put them all together

        // return Collections.singletonList(new CatalogItem("ThunderStorm","Test Description",4));
    }

    */
