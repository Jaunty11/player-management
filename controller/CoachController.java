package com.yourname.player_management.controller;

import com.yourname.player_management.dto.CoachRequest;
import com.yourname.player_management.dto.CoachResponse;
import com.yourname.player_management.service.CoachService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
@RequiredArgsConstructor
public class CoachController {

    private final CoachService coachService;

    @PostMapping
    public ResponseEntity<CoachResponse> createCoach(@Valid @RequestBody CoachRequest request) {
        CoachResponse response = coachService.createCoach(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoachResponse> getCoachById(@PathVariable Long id) {
        CoachResponse response = coachService.getCoachById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CoachResponse>> getAllCoaches() {
        List<CoachResponse> coaches = coachService.getAllCoaches();
        return ResponseEntity.ok(coaches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoachResponse> updateCoach(
            @PathVariable Long id,
            @Valid @RequestBody CoachRequest request) {
        CoachResponse response = coachService.updateCoach(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Long id) {
        coachService.deleteCoach(id);
        return ResponseEntity.noContent().build();
    }
}
