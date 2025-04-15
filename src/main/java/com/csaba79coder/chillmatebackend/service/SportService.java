package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.Sport;
import com.csaba79coder.chillmatebackend.model.SportRequest;
import com.csaba79coder.chillmatebackend.model.SportResponse;
import com.csaba79coder.chillmatebackend.persistence.SportRepository;
import com.csaba79coder.chillmatebackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapSportEntityToResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class SportService implements GetOrCreateService<Sport, SportRequest, SportResponse> {

    private final SportRepository sportRepository;

    public SportResponse createSport(SportRequest sportRequest) {
        Sport sport = Sport.builder()
                .name(sportRequest.getName())
                .build();
        log.info("Creating sport: {}", sport);
        return mapSportEntityToResponse(sportRepository.save(sport));
    }

    public List<SportResponse> getAllSports() {
        return sportRepository.findAll().stream()
                .map(Mapper::mapSportEntityToResponse)
                .collect(Collectors.toList());
    }

    public Sport findSportById(UUID id) {
        return sportRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Sport with id: %s was not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
    }

    public SportResponse findByName(String name) {
        return sportRepository.findSportByNameEqualsIgnoreCase(name)
                .orElseThrow(() -> {
                    String message = String.format("Sport with name: %s was not found", name);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
    }

    public void deleteSport(UUID id) {
        Sport sport = findSportById(id);
        sportRepository.delete(sport);
        log.info("Sport with id: {} deleted successfully", id);
    }

    @Override
    public SportResponse getOrCreate(String name) {
        return sportRepository.findSportByNameEqualsIgnoreCase(name)
                .orElseGet(() -> {
                    Sport sport = Sport.builder().name(name).build();
                    log.info("Creating new sport with name: {}", name);
                    return mapSportEntityToResponse(sportRepository.save(sport));
                });
    }
}
