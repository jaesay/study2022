package com.jaesay.apiclient;

import com.jaesay.domain.movie.Movie;
import com.jaesay.util.CommonUtil;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieClientTest {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/movies")
            .build();

    MovieClient movieClient = new MovieClient(webClient);

    @RepeatedTest(10)
    void retrieveMovie() {
        // given
        long movieInfoId = 1L;
        CommonUtil.startTimer();

        // when
        Movie movie = movieClient.retrieveMovie(movieInfoId);

        // then
        CommonUtil.timeTaken();
        assert movie != null;
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }

    @RepeatedTest(10)
    void retrieveMovie_CF() {
        // given
        long movieInfoId = 1L;
        CommonUtil.startTimer();

        // when
        Movie movie = movieClient.retrieveMovie_CF(movieInfoId).join();

        // then
        CommonUtil.timeTaken();
        assert movie != null;
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }

    @RepeatedTest(10)
    void retrieveMovies() {
        // given
        var movieInfoIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);
        CommonUtil.startTimer();

        // when
        var movies = movieClient.retrieveMovies(movieInfoIds);

        // then
        CommonUtil.timeTaken();
        assert movies != null;
        assert movies.size() == 7;
    }

    @RepeatedTest(10)
    void retrieveMovies_CF() {
        // given
        var movieInfoIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);
        CommonUtil.startTimer();

        // when
        var movies = movieClient.retrieveMovies_CF(movieInfoIds);

        // then
        CommonUtil.timeTaken();
        assert movies != null;
        assert movies.size() == 7;
    }

    @RepeatedTest(10)
    void retrieveMovies_CF_allOf() {
        // given
        var movieInfoIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);
        CommonUtil.startTimer();

        // when
        var movies = movieClient.retrieveMovies_CF_allOf(movieInfoIds);

        // then
        CommonUtil.timeTaken();
        assert movies != null;
        assert movies.size() == 7;
    }
}