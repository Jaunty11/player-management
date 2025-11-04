package com.yourname.player_management.service;

import com.yourname.player_management.dto.CoachRequest;
import com.yourname.player_management.dto.CoachResponse;
import com.yourname.player_management.entity.Coach;
import com.yourname.player_management.exception.ResourceNotFoundException;
import com.yourname.player_management.mapper.CoachMapper;
import com.yourname.player_management.repository.CoachRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoachService {

    private final CoachRepository coachRepository;
    private final CoachMapper coachMapper;

    @Transactional
    public CoachResponse createCoach(CoachRequest request) {
        log.info("Creating coach: {}", request.getName());
        Coach coach = coachMapper.toEntity(request);
        Coach savedCoach = coachRepository.save(coach);
        log.info("Coach created with ID: {}", savedCoach.getId());
        return coachMapper.toResponse(savedCoach);
    }

    public CoachResponse getCoachById(Long id) {
        log.info("Fetching coach with ID: {}", id);
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coach not found"));
        return coachMapper.toResponse(coach);
    }

    public List<CoachResponse> getAllCoaches() {
        log.info("Fetching all coaches");
        return coachRepository.findAll().stream()
                .map(coachMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CoachResponse updateCoach(Long id, CoachRequest request) {
        log.info("Updating coach with ID: {}", id);
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coach not found"));

        coach.setName(request.getName());
        coach.setNationality(request.getNationality());
        coach.setSpecialty(request.getSpecialty());
        coach.setExperience(request.getExperience());

        Coach updatedCoach = coachRepository.save(coach);
        log.info("Coach updated: {}", id);
        return coachMapper.toResponse(updatedCoach);
    }

    @Transactional
    public void deleteCoach(Long id) {
        log.info("Deleting coach with ID: {}", id);
        if (!coachRepository.existsById(id)) {
            throw new ResourceNotFoundException("Coach not found");
        }
        coachRepository.deleteById(id);
        log.info("Coach deleted: {}", id);
    }
}
