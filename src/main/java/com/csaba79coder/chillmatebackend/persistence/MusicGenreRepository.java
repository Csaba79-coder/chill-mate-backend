package com.csaba79coder.chillmatebackend.persistence;

import com.csaba79coder.chillmatebackend.entity.MusicGenre;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MusicGenreRepository extends Neo4jRepository<MusicGenre, UUID> {

    Optional<MusicGenre> findMusicGenreByGenreEqualsIgnoreCase(String genre);
}
