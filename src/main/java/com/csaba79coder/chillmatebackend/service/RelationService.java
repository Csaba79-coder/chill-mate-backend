package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.User;
import com.csaba79coder.chillmatebackend.model.UserGraphResponse;
import com.csaba79coder.chillmatebackend.persistence.UserRepository;
import com.csaba79coder.chillmatebackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelationService {

    private final UserRepository userRepository;

    public List<UserGraphResponse> getAllUsersForGraph() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(Mapper::mapToUserGraphResponse)
                .collect(Collectors.toList());
    }
}
