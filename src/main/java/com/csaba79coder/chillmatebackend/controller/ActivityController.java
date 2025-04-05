package com.csaba79coder.chillmatebackend.controller;

import com.csaba79coder.chillmatebackend.model.ActivityRequest;
import com.csaba79coder.chillmatebackend.model.ActivityResponse;
import com.csaba79coder.chillmatebackend.service.ActivityService;
import com.csaba79coder.chillmatebackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/activities")
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityRequest activityRequest) {
        ActivityResponse createdActivity = activityService.createActivity(activityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
    }

    @GetMapping("/activities")
    public ResponseEntity<List<ActivityResponse>> renderAllActivities() {
        return ResponseEntity.status(200).body(activityService.getAllActivities());
    }

    @GetMapping("/activities/activity/{id}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(Mapper.mapActivityEntityToResponse(activityService.findActivityById(id)));
    }

    @GetMapping("/activities/activity/{name}")
    public ResponseEntity<ActivityResponse> searchActivityByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(activityService.findByName(name));
    }

    @DeleteMapping("/activities/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable UUID id) {
        activityService.deleteActivity(id);
        return ResponseEntity.status(204).build();
    }
}
