package com.ms.moviecatalogservice.service;

import com.ms.moviecatalogservice.model.CatalogItem;
import com.ms.moviecatalogservice.model.Movie;
import com.ms.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;


    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
    }

}
