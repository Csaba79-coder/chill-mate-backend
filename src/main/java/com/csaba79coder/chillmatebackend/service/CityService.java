package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.City;
import com.csaba79coder.chillmatebackend.model.CityRequest;
import com.csaba79coder.chillmatebackend.model.CityResponse;
import com.csaba79coder.chillmatebackend.model.SportResponse;
import com.csaba79coder.chillmatebackend.persistence.CityRepository;
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

import static com.csaba79coder.chillmatebackend.util.Mapper.mapCityEntityToResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService implements GetOrCreateService<City, CityRequest, CityResponse>{

    private final CityRepository cityRepository;

    public CityResponse createCity(CityRequest cityRequest) {
        if (cityRequest.getName() == null || cityRequest.getName().trim().isEmpty()) {
            String message = "City name is null. No action taken.";
            log.info(message);
            return new CityResponse(message, HttpStatus.BAD_REQUEST.value());
        }
        Optional<CityResponse> existingCity = cityRepository.findCityByNameEqualsIgnoreCase(cityRequest.getName());
        if (existingCity.isPresent()) {
            log.info("City with name '{}' already exists. Updating...", cityRequest.getName());
            return existingCity.get();
        }
        City city = City.builder()
                .name(cityRequest.getName())
                .build();
        log.info("Creating city: {}", city);
        return mapCityEntityToResponse(cityRepository.save(city));
    }

    public List<CityResponse> getAllCities() {
        return cityRepository.findAll().stream()
                .map(Mapper::mapCityEntityToResponse)
                .collect(Collectors.toList());
    }

    public City findCityById(UUID id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("City with id: %s was not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
    }

    public CityResponse findByName(String name) {
        return cityRepository.findCityByNameEqualsIgnoreCase(name)
                .orElseThrow(() -> {
                    String message = String.format("City with name: %s was not found", name);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
    }

    public void deleteCity(UUID id) {
        City city = findCityById(id);
        cityRepository.delete(city);
        log.info("City with id: {} deleted successfully", id);
    }

    @Override
    public CityResponse getOrCreate(String name) {
        try {
            return findByName(name);
        } catch (NoSuchElementException e) {
            City city = City.builder().name(name).build();
            log.info("Creating new city with name: {}", name);
            return mapCityEntityToResponse(cityRepository.save(city));
        }
    }
}
