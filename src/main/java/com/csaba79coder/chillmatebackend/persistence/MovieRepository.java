package com.csaba79coder.chillmatebackend.persistence;

import com.csaba79coder.chillmatebackend.entity.Movie;
import com.csaba79coder.chillmatebackend.model.MovieResponse;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie, UUID> {

    Optional<MovieResponse> findMovieByTitleEqualsIgnoreCase(String title);
}
