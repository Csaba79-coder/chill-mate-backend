package com.csaba79coder.chillmatebackend.persistence;

import com.csaba79coder.chillmatebackend.entity.Sport;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SportRepository extends Neo4jRepository<Sport, UUID> {

    Optional<Sport> findSportByNameEqualsIgnoreCase(String name);
}
