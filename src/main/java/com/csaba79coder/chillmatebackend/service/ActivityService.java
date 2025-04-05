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

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityResponse createActivity(ActivityRequest activityRequest) {
        Activity activity = new Activity();
        activity.setName(activityRequest.getName());
        Activity savedActivity = activityRepository.save(activity);
        return Mapper.mapActivityEntityToResponse(savedActivity);
    }

    public List<ActivityResponse> getAllActivities() {
        return activityRepository.findAll().stream()
                .map(Mapper::mapActivityEntityToResponse)
                .collect(Collectors.toList());
    }

    public ActivityResponse findActivityById(UUID id) {
        return Mapper.mapActivityEntityToResponse(activityRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Activity with id: %s was not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                }));
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
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Activity with id: %s was not found", id);
                    log.info(message);
                    return new NoSuchElementException("Activity not found");
                });
        activityRepository.delete(activity);
        log.info("Activity with id: {} deleted successfully", id);
    }
}
