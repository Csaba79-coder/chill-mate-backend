package com.csaba79coder.chillmatebackend.controller;

import com.csaba79coder.chillmatebackend.model.MusicGenreRequest;
import com.csaba79coder.chillmatebackend.model.MusicGenreResponse;
import com.csaba79coder.chillmatebackend.service.MusicGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapMusicGenreEntityToResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MusicGenreController {

    private final MusicGenreService musicGenreService;

    @PostMapping("/music-genres")
    public ResponseEntity<MusicGenreResponse> createMusicGenre(@RequestBody MusicGenreRequest musicGenreRequest) {
        MusicGenreResponse musicGenreResponse = musicGenreService.createMusicGenre(musicGenreRequest);
        return ResponseEntity.status(201).body(musicGenreResponse);
    }

    @GetMapping("/music-genres")
    public ResponseEntity<List<MusicGenreResponse>> renderAllMusicGenres() {
        return ResponseEntity.status(200).body(musicGenreService.getAllMusicGenres());
    }

    @GetMapping("/music-genres/music-genre-by-id/{id}")
    public ResponseEntity<MusicGenreResponse> getMusicGenreById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(mapMusicGenreEntityToResponse(musicGenreService.findMusicGenreById(id)));
    }

    @GetMapping("/music-genres/music-genre-by-name/{name}")
    public ResponseEntity<MusicGenreResponse> searchMusicGenreByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(musicGenreService.findMusicGenreByName(name));
    }

    @DeleteMapping("/music-genres/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        musicGenreService.deleteMusicGenre(id);
        return ResponseEntity.status(204).build();
    }
}
