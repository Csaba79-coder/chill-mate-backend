package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.Movie;
import com.csaba79coder.chillmatebackend.model.MovieRequest;
import com.csaba79coder.chillmatebackend.model.MovieResponse;
import com.csaba79coder.chillmatebackend.persistence.MovieRepository;
import com.csaba79coder.chillmatebackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapMovieEntityToResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieResponse createMovie(MovieRequest movieRequest) {
        if (movieRequest.getTitle() == null || movieRequest.getTitle().trim().isEmpty()) {
            String message = "Movie title is null. No action taken.";
            log.info(message);
            return new MovieResponse(message, HttpStatus.BAD_REQUEST.value());
        }
        Optional<Movie> existingMovie = movieRepository.findMovieByTitleEqualsIgnoreCase(movieRequest.getTitle());
        if (existingMovie.isPresent()) {
            log.info("Movie with title '{}' already exists. Updating...", movieRequest.getTitle());
            return mapMovieEntityToResponse(existingMovie.get());
        }
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

    public MovieResponse findMovieByName(String title) {
        return mapMovieEntityToResponse(movieRepository.findMovieByTitleEqualsIgnoreCase(title)
                .orElseThrow(() -> {
                    String message = String.format("Movie with title: %s was not found", title);
                    log.info(message);
                    return new NoSuchElementException(message);
                }));
    }

    public void deleteMovie(UUID id) {
        Movie movie = findMovieById(id);
        movieRepository.delete(movie);
        log.info("Movie with id: {} deleted successfully", id);
    }
}
