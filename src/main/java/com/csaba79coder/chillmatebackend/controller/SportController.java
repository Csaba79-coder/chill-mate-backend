package com.csaba79coder.chillmatebackend.controller;

import com.csaba79coder.chillmatebackend.model.SportRequest;
import com.csaba79coder.chillmatebackend.model.SportResponse;
import com.csaba79coder.chillmatebackend.service.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapSportEntityToResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SportController {

    private final SportService sportService;

    @PostMapping("/sports")
    public ResponseEntity<SportResponse> createSport(@RequestBody SportRequest sportRequest) {
        SportResponse sportResponse = sportService.createSport(sportRequest);
        return ResponseEntity.status(201).body(sportResponse);
    }

    @GetMapping("/sports")
    public ResponseEntity<List<SportResponse>> renderAllSports() {
        return ResponseEntity.status(200).body(sportService.getAllSports());
    }

    @GetMapping("/sports/sport-by-id/{id}")
    public ResponseEntity<SportResponse> getSportById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(mapSportEntityToResponse(sportService.findSportById(id)));
    }

    @GetMapping("/sports/sport-by-name/{name}")
    public ResponseEntity<SportResponse> searchSportByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(sportService.findByName(name));
    }

    @DeleteMapping("/sports/{id}")
    public ResponseEntity<Void> deleteSport(@PathVariable UUID id) {
        sportService.deleteSport(id);
        return ResponseEntity.status(204).build();
    }
}
