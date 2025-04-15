package com.csaba79coder.chillmatebackend.persistence;

import com.csaba79coder.chillmatebackend.entity.Hobby;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HobbyRepository extends Neo4jRepository<Hobby, UUID> {

    Optional<Hobby> findHobbyByNameEqualsIgnoreCase(String name);
}