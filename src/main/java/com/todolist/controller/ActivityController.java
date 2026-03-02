package com.todolist.controller;

import com.todolist.dto.ActivityDTO;
import com.todolist.model.ActivityStatus;
import com.todolist.service.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Para teste inicial. No futuro, coloque apenas a URL do seu Front.
@RestController
@RequestMapping("/api/projects/{projectId}/activities")
@RequiredArgsConstructor
public class ActivityController {

	@Autowired
    private ActivityService activityService;

    @GetMapping
    public ResponseEntity<List<ActivityDTO.Response>> findAll(
            @PathVariable Long projectId,
            @RequestParam(required = false) ActivityStatus status) {
        return ResponseEntity.ok(activityService.findByProject(projectId, status));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityDTO.Response> findById(
            @PathVariable Long projectId,
            @PathVariable Long activityId) {
        return ResponseEntity.ok(activityService.findById(projectId, activityId));
    }

    @PostMapping
    public ResponseEntity<ActivityDTO.Response> create(
            @PathVariable Long projectId,
            @Valid @RequestBody ActivityDTO.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activityService.create(projectId, request));
    }

    @PutMapping("/{activityId}")
    public ResponseEntity<ActivityDTO.Response> update(
            @PathVariable Long projectId,
            @PathVariable Long activityId,
            @Valid @RequestBody ActivityDTO.Request request) {
        return ResponseEntity.ok(activityService.update(projectId, activityId, request));
    }

    @PatchMapping("/{activityId}/status")
    public ResponseEntity<ActivityDTO.Response> updateStatus(
            @PathVariable Long projectId,
            @PathVariable Long activityId,
            @RequestBody ActivityDTO.StatusRequest request) {
        return ResponseEntity.ok(activityService.updateStatus(projectId, activityId, request));
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long projectId,
            @PathVariable Long activityId) {
        activityService.delete(projectId, activityId);
        return ResponseEntity.noContent().build();
    }
}
