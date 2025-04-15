package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.Activity;
import com.csaba79coder.chillmatebackend.model.ActivityRequest;
import com.csaba79coder.chillmatebackend.model.ActivityResponse;
import com.csaba79coder.chillmatebackend.persistence.ActivityRepository;
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

import static com.csaba79coder.chillmatebackend.util.Mapper.mapActivityEntityToResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService implements GetOrCreateService<Activity, ActivityRequest, ActivityResponse>{

    private final ActivityRepository activityRepository;

    public ActivityResponse createActivity(ActivityRequest activityRequest) {
        if (activityRequest.getName() == null || activityRequest.getName().trim().isEmpty()) {
            String message = "Activity name is null or empty. No action taken.";
            log.info(message);
            return new ActivityResponse(message, HttpStatus.BAD_REQUEST.value());
        }
        Optional<ActivityResponse> existingActivity = activityRepository.findActivityByNameEqualsIgnoreCase(activityRequest.getName());
        if (existingActivity.isPresent()) {
            log.info("Activity with name '{}' already exists.", activityRequest.getName());
            return existingActivity.get();
        }
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

    public ActivityResponse findActivityByName(String name) {
        return activityRepository.findActivityByNameEqualsIgnoreCase(name)
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

    @Override
    public ActivityResponse getOrCreate(String name) {
        return activityRepository.findActivityByNameEqualsIgnoreCase(name)
                .orElseGet(() -> {
                    Activity newActivity = Activity.builder().name(name).build();
                    log.info("Creating new activity with name: {}", name);
                    return mapActivityEntityToResponse(activityRepository.save(newActivity));
                });
    }
}
