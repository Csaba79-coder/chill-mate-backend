package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.model.ActivityResponse;
import com.csaba79coder.chillmatebackend.persistence.ActivityRepository;
import com.csaba79coder.chillmatebackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapActivityEntityToResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService {

    private final ActivityRepository activityRepository;

    public List<ActivityResponse> getAllActivities() {
        return activityRepository.findAll().stream()
                .map(Mapper::mapActivityEntityToResponse)
                .collect(Collectors.toList());
    }

    public ActivityResponse findActivityById(UUID id) {
        return mapActivityEntityToResponse(activityRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Activity with id: %s was not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                }));
    }
}
