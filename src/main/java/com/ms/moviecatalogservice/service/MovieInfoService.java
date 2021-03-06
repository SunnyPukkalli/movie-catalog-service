package com.ms.moviecatalogservice.service;

import com.ms.moviecatalogservice.model.CatalogItem;
import com.ms.moviecatalogservice.model.Movie;
import com.ms.moviecatalogservice.model.Rating;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;


//    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
    }
/*

    private CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie Not Found","",rating.getRating());
    }

*/

}
