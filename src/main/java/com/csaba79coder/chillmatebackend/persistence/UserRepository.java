package com.csaba79coder.chillmatebackend.persistence;

import com.csaba79coder.chillmatebackend.entity.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends Neo4jRepository<User, UUID> {

    Optional<User> findUserByFirstNameEqualsIgnoreCaseAndMidNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase(String firstName,
                                                                                                            String midName,
                                                                                                            String lastName);
    List<User> findByFirstNameContainingIgnoreCaseOrMidNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String queryFirst,
            String queryMid,
            String queryLast
    );
}
