package com.csaba79coder.chillmatebackend.persistence;

import com.csaba79coder.chillmatebackend.entity.City;
import com.csaba79coder.chillmatebackend.model.CityResponse;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends Neo4jRepository<City, UUID> {

    Optional<CityResponse> findCityByNameEqualsIgnoreCase(String name);
}

