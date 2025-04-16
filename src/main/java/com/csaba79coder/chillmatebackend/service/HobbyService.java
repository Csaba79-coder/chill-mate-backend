package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.Hobby;
import com.csaba79coder.chillmatebackend.model.HobbyRequest;
import com.csaba79coder.chillmatebackend.model.HobbyResponse;
import com.csaba79coder.chillmatebackend.persistence.HobbyRepository;
import com.csaba79coder.chillmatebackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapHobbyEntityToResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    public HobbyResponse createHobby(HobbyRequest hobbyRequest) {
        if (hobbyRequest.getName() == null || hobbyRequest.getName().trim().isEmpty()) {
            String message = "Hobby name is null. No action taken.";
            log.info(message);
            return new HobbyResponse(message, HttpStatus.BAD_REQUEST.value());
        }
        Optional<Hobby> existingHobby = hobbyRepository.findHobbyByNameEqualsIgnoreCase(hobbyRequest.getName());
        if (existingHobby.isPresent()) {
            log.info("Hobby with name '{}' already exists. Updating...", hobbyRequest.getName());
            return mapHobbyEntityToResponse(existingHobby.get());
        }
        Hobby hobby = Hobby.builder()
                .name(hobbyRequest.getName())
                .build();
        log.info("Creating hobby: {}", hobby);
        return mapHobbyEntityToResponse(hobbyRepository.save(hobby));
    }

    public List<HobbyResponse> getAllHobbies() {
        return hobbyRepository.findAll().stream()
                .map(Mapper::mapHobbyEntityToResponse)
                .collect(Collectors.toList());
    }

    public Hobby findHobbyById(UUID id) {
        return hobbyRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Hobby with id: %s was not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
    }

    public HobbyResponse findHobbyByName(String name) {
        return mapHobbyEntityToResponse(hobbyRepository.findHobbyByNameEqualsIgnoreCase(name)
                .orElseThrow(() -> {
                    String message = String.format("Hobby with name: %s was not found", name);
                    log.info(message);
                    return new NoSuchElementException(message);
                }));
    }

    public void deleteHobby(UUID id) {
        Hobby hobby = findHobbyById(id);
        hobbyRepository.delete(hobby);
        log.info("Hobby with id: {} deleted successfully", id);
    }
}
