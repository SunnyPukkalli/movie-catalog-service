package com.ms.moviecatalogservice.service;

import com.ms.moviecatalogservice.model.CatalogItem;
import com.ms.moviecatalogservice.model.Movie;
import com.ms.moviecatalogservice.model.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    Logger logger = LoggerFactory.getLogger(MovieInfoService.class);

    @Autowired
    private RestTemplate restTemplate;

    public CatalogItem getCatalogItem(Rating rating) {

        logger.info("Performing REST API Call to movie-info.");

        Movie movie = restTemplate.getForObject("http://movie-info/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
    }

}
