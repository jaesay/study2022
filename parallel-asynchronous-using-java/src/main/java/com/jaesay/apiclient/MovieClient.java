package com.jaesay.apiclient;

import com.jaesay.domain.movie.Movie;
import com.jaesay.domain.movie.MovieInfo;
import com.jaesay.domain.movie.Review;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MovieClient {

    private final WebClient webClient;

    public MovieClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Movie retrieveMovie(long movieInfoId) {
        MovieInfo movieInfo = invokeMovieInfoService(movieInfoId);
        List<Review> reviews = invokeReviewsService(movieInfoId);
        return new Movie(movieInfo, reviews);
    }

    public List<Movie> retrieveMovies(List<Long> movieInfoIds) {
        return movieInfoIds.stream()
                .map(this::retrieveMovie)
                .collect(Collectors.toList());
    }

    public CompletableFuture<Movie> retrieveMovie_CF(long movieInfoId) {
        var movieCF = CompletableFuture.supplyAsync(() -> invokeMovieInfoService(movieInfoId));
        var reviewsCF = CompletableFuture.supplyAsync(() -> invokeReviewsService(movieInfoId));
        return movieCF.thenCombine(reviewsCF, Movie::new);
    }

    public List<Movie> retrieveMovies_CF(List<Long> movieInfoIds) {
        var movieFutures = movieInfoIds.stream()
                .map(this::retrieveMovie_CF)
                .collect(Collectors.toList());

        return movieFutures.stream()
                .map(CompletableFuture::join) // background에서 실행되고 있긴 하지만 blocking calls
                .collect(Collectors.toList());
    }

    public List<Movie> retrieveMovies_CF_allOf(List<Long> movieInfoIds) {
        var movieFutures = movieInfoIds.stream()
                .map(this::retrieveMovie_CF)
                .collect(Collectors.toList());

        var cfAllof = CompletableFuture.allOf(movieFutures.toArray(new CompletableFuture[movieFutures.size()])); // 모든 작업이 끝난 후

        return cfAllof.thenApply(v -> movieFutures.stream()
                        .map(CompletableFuture::join) // 바로 가져옴
                        .collect(Collectors.toList()))
                .join();
    }

    private MovieInfo invokeMovieInfoService(long movieInfoId) {
        String uri = "/v1/movie_infos/{movieInfoId}";

        return webClient.get()
                .uri(uri, movieInfoId)
                .retrieve()
                .bodyToMono(MovieInfo.class)
                .block();
    }

    private List<Review> invokeReviewsService(long movieInfoId) {
        String uri = UriComponentsBuilder.fromUriString("/v1/reviews")
                .queryParam("movieInfoId", movieInfoId)
                .buildAndExpand()
                .toUriString();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .block();
    }
}
