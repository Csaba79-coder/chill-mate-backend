package com.csaba79coder.chillmatebackend.persistence;

import com.csaba79coder.chillmatebackend.entity.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie, UUID> {
}
