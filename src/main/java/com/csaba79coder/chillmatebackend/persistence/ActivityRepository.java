package com.csaba79coder.chillmatebackend.persistence;

import com.csaba79coder.chillmatebackend.entity.Activity;
import com.csaba79coder.chillmatebackend.model.ActivityResponse;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivityRepository extends Neo4jRepository<Activity, UUID> {

    Optional<ActivityResponse> findActivityByNameEqualsIgnoreCase(String name);
}
