package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.Movie;
import com.csaba79coder.chillmatebackend.model.MovieRequest;
import com.csaba79coder.chillmatebackend.model.MovieResponse;
import com.csaba79coder.chillmatebackend.persistence.MovieRepository;
import com.csaba79coder.chillmatebackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapMovieEntityToResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService implements GetOrCreateService<Movie, MovieRequest, MovieResponse> {

    private final MovieRepository movieRepository;

    public MovieResponse createMovie(MovieRequest movieRequest) {
        Movie movie = Movie.builder()
                .title(movieRequest.getTitle())
                .build();
        log.info("Creating movie: {}", movie);
        return mapMovieEntityToResponse(movieRepository.save(movie));
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(Mapper::mapMovieEntityToResponse)
                .collect(Collectors.toList());
    }

    public Movie findMovieById(UUID id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Movie with id: %s was not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
    }

    public MovieResponse findByName(String title) {
        return movieRepository.findMovieByTitleEqualsIgnoreCase(title)
                .orElseThrow(() -> {
                    String message = String.format("Movie with title: %s was not found", title);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
    }

    public void deleteMovie(UUID id) {
        Movie movie = findMovieById(id);
        movieRepository.delete(movie);
        log.info("Movie with id: {} deleted successfully", id);
    }

    @Override
    public MovieResponse getOrCreate(String name) {
        return movieRepository.findMovieByTitleEqualsIgnoreCase(name)
                .orElseGet(() -> {
                    Movie movie = Movie.builder().title(name).build();
                    log.info("Creating new movie with name: {}", name);
                    return mapMovieEntityToResponse(movieRepository.save(movie));
                });
    }
}
