package com.ms.moviecatalogservice.service;

import com.ms.moviecatalogservice.model.CatalogItem;
import com.ms.moviecatalogservice.model.Movie;
import com.ms.moviecatalogservice.model.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    Logger logger = LoggerFactory.getLogger(MovieInfoService.class);

    @Value("${movie.info:default}")
    private String MOVIE_INFO_URL;

    @Autowired
    private RestTemplate restTemplate;

    public CatalogItem getCatalogItem(Rating rating) {

        String URL = getURL(rating.getMovieId());
        logger.info("Performing REST API Call to movie-info to URL :- "+URL);

        Movie movie = restTemplate.getForObject(URL, Movie.class);
        return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
    }

    private String getURL(int movieId) {
        return MOVIE_INFO_URL+movieId;
    }

}
