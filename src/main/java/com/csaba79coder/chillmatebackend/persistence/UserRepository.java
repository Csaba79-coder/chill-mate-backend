package com.csaba79coder.chillmatebackend.persistence;

import com.csaba79coder.chillmatebackend.entity.User;
import com.csaba79coder.chillmatebackend.model.UserResponse;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends Neo4jRepository<User, UUID> {

    Optional<UserResponse> findUserByFistNameEqualsIgnoreCaseAndMidNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase(String firstName, String midName, String lastName);
}
