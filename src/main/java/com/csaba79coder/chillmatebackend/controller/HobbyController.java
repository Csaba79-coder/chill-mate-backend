package com.csaba79coder.chillmatebackend.controller;

import com.csaba79coder.chillmatebackend.model.HobbyRequest;
import com.csaba79coder.chillmatebackend.model.HobbyResponse;
import com.csaba79coder.chillmatebackend.service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapHobbyEntityToResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HobbyController {

    private final HobbyService hobbyService;

    @PostMapping("/hobbies")
    public ResponseEntity<HobbyResponse> createHobby(@RequestBody HobbyRequest hobbyRequest) {
        HobbyResponse hobbyResponse = hobbyService.createHobby(hobbyRequest);
        return ResponseEntity.status(201).body(hobbyResponse);
    }

    @GetMapping("/hobbies")
    public ResponseEntity<List<HobbyResponse>> renderAllHobbies() {
        return ResponseEntity.status(200).body(hobbyService.getAllHobbies());
    }

    @GetMapping("/hobbies/hobby-by-id/{id}")
    public ResponseEntity<HobbyResponse> getHobbyById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(mapHobbyEntityToResponse(hobbyService.findHobbyById(id)));
    }

    @GetMapping("/hobbies/hobby-by-name/{name}")
    public ResponseEntity<HobbyResponse> searchHobbyByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(hobbyService.findByName(name));
    }

    @DeleteMapping("/hobbies/{id}")
    public ResponseEntity<Void> deleteHobby(@PathVariable UUID id) {
        hobbyService.deleteHobby(id);
        return ResponseEntity.status(204).build();
    }
}
