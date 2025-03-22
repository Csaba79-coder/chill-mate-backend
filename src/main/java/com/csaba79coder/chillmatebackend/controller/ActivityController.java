package com.csaba79coder.chillmatebackend.controller;

import com.csaba79coder.chillmatebackend.model.ActivityResponse;
import com.csaba79coder.chillmatebackend.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/activities")
    public ResponseEntity<List<ActivityResponse>> renderAllActivities() {
        return ResponseEntity.status(200).body(activityService.getAllActivities());
    }

    @GetMapping("/activities/activity/{id}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(activityService.findActivityById(id));
    }
}
