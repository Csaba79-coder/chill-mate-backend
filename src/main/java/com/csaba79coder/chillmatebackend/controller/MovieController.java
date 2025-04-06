package com.csaba79coder.chillmatebackend.controller;

import com.csaba79coder.chillmatebackend.model.MovieRequest;
import com.csaba79coder.chillmatebackend.model.MovieResponse;
import com.csaba79coder.chillmatebackend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapMovieEntityToResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest movieRequest) {
        MovieResponse movieResponse = movieService.createMovie(movieRequest);
        return ResponseEntity.status(201).body(movieResponse);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> renderAllMovies() {
        return ResponseEntity.status(200).body(movieService.getAllMovies());
    }

    @GetMapping("/movies/movie-by-id/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(mapMovieEntityToResponse(movieService.findMovieById(id)));
    }

    @GetMapping("/movies/movie-by-name/{name}")
    public ResponseEntity<MovieResponse> searchMovieByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(movieService.findByName(name));
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        movieService.deleteMovie(id);
        return ResponseEntity.status(204).build();
    }
}
