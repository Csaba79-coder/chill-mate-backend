package com.csaba79coder.chillmatebackend.controller;

import com.csaba79coder.chillmatebackend.model.CityRequest;
import com.csaba79coder.chillmatebackend.model.CityResponse;
import com.csaba79coder.chillmatebackend.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapCityEntityToResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CityController {

    private final CityService cityService;

    @PostMapping("/cities")
    public ResponseEntity<CityResponse> createCity(@RequestBody CityRequest cityRequest) {
        CityResponse cityResponse = cityService.createCity(cityRequest);
        return ResponseEntity.status(201).body(cityResponse);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityResponse>> renderAllCities() {
        return ResponseEntity.status(200).body(cityService.getAllCities());
    }

    @GetMapping("/cities/city-by-id/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(mapCityEntityToResponse(cityService.findCityById(id)));
    }

    @GetMapping("/cities/city-by-name/{name}")
    public ResponseEntity<CityResponse> searchCityByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(cityService.findCityByName(name));
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable UUID id) {
        cityService.deleteCity(id);
        return ResponseEntity.status(204).build();
    }
}
