package com.csaba79coder.chillmatebackend.controller;

import com.csaba79coder.chillmatebackend.model.ActivityRequest;
import com.csaba79coder.chillmatebackend.model.ActivityResponse;
import com.csaba79coder.chillmatebackend.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapActivityEntityToResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/activities")
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityRequest activityRequest) {
        ActivityResponse activityResponse = activityService.createActivity(activityRequest);
        return ResponseEntity.status(201).body(activityResponse);
    }

    @GetMapping("/activities")
    public ResponseEntity<List<ActivityResponse>> renderAllActivities() {
        return ResponseEntity.status(200).body(activityService.getAllActivities());
    }

    @GetMapping("/activities/activity-by-id/{id}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(mapActivityEntityToResponse(activityService.findActivityById(id)));
    }

    @GetMapping("/activities/activity-by-name/{name}")
    public ResponseEntity<ActivityResponse> searchActivityByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(activityService.findActivityByName(name));
    }

    @DeleteMapping("/activities/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable UUID id) {
        activityService.deleteActivity(id);
        return ResponseEntity.status(204).build();
    }
}
