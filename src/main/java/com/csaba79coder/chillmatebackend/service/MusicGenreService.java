package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.MusicGenre;
import com.csaba79coder.chillmatebackend.model.MusicGenreRequest;
import com.csaba79coder.chillmatebackend.model.MusicGenreResponse;
import com.csaba79coder.chillmatebackend.persistence.MusicGenreRepository;
import com.csaba79coder.chillmatebackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapMusicGenreEntityToResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicGenreService {

    private final MusicGenreRepository musicGenreRepository;

    public MusicGenreResponse createMusicGenre(MusicGenreRequest musicGenreRequest) {
        MusicGenre musicGenre = MusicGenre.builder()
                .genre(musicGenreRequest.getGenre())
                .build();
        log.info("Creating music genre: {}", musicGenre);
        return mapMusicGenreEntityToResponse(musicGenreRepository.save(musicGenre));
    }

    public List<MusicGenreResponse> getAllMusicGenres() {
        return musicGenreRepository.findAll().stream()
                .map(Mapper::mapMusicGenreEntityToResponse)
                .collect(Collectors.toList());
    }

    public MusicGenre findMusicGenreById(UUID id) {
        return musicGenreRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Music genre with id: %s was not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
    }

    public MusicGenreResponse findByName(String genre) {
        return musicGenreRepository.findMusicGenreByGenreEqualsIgnoreCase(genre)
                .orElseThrow(() -> {
                    String message = String.format("Music genre with name: %s was not found", genre);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
    }

    public void deleteMusicGenre(UUID id) {
        MusicGenre musicGenre = findMusicGenreById(id);
        musicGenreRepository.delete(musicGenre);
        log.info("Music genre with id: {} deleted successfully", id);
    }
}
