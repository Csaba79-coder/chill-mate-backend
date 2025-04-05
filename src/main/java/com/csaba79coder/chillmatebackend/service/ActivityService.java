package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.Activity;
import com.csaba79coder.chillmatebackend.model.ActivityRequest;
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

    public ActivityResponse createActivity(ActivityRequest activityRequest) {
        Activity activity = Activity.builder()
                .name(activityRequest.getName())
                .build();
        log.info("Creating activity: {}", activity);
        return mapActivityEntityToResponse(activityRepository.save(activity));
    }

    public List<ActivityResponse> getAllActivities() {
        return activityRepository.findAll().stream()
                .map(Mapper::mapActivityEntityToResponse)
                .collect(Collectors.toList());
    }

    public Activity findActivityById(UUID id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Activity with id: %s was not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
    }

    public ActivityResponse findByName(String name) {
        return activityRepository.findByNameEqualsIgnoreCase(name)
                .orElseThrow(() -> {
                    String message = String.format("Activity with name: %s was not found", name);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
    }

    public void deleteActivity(UUID id) {
        Activity activity = findActivityById(id);
        activityRepository.delete(activity);
        log.info("Activity with id: {} deleted successfully", id);
    }
}
