package com.yourname.player_management.service;

import com.yourname.player_management.dto.TeamRequest;
import com.yourname.player_management.dto.TeamResponse;
import com.yourname.player_management.entity.Coach;
import com.yourname.player_management.entity.Team;
import com.yourname.player_management.exception.ResourceNotFoundException;
import com.yourname.player_management.mapper.TeamMapper;
import com.yourname.player_management.repository.CoachRepository;
import com.yourname.player_management.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;
    private final TeamMapper teamMapper;

    @Transactional
    public TeamResponse createTeam(TeamRequest request) {
        log.info("Creating team: {}", request.getName());

        Team team = teamMapper.toEntity(request);

        if (request.getCoachId() != null) {
            Coach coach = coachRepository.findById(request.getCoachId())
                    .orElseThrow(() -> new ResourceNotFoundException("Coach not found"));
            team.setCoach(coach);
        }

        Team savedTeam = teamRepository.save(team);
        log.info("Team created with ID: {}", savedTeam.getId());
        return teamMapper.toResponse(savedTeam);
    }

    public TeamResponse getTeamById(Long id) {
        log.info("Fetching team with ID: {}", id);
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));
        return teamMapper.toResponseWithPlayers(team);
    }

    public List<TeamResponse> getAllTeams() {
        log.info("Fetching all teams");
        return teamRepository.findAll().stream()
                .map(teamMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public TeamResponse updateTeam(Long id, TeamRequest request) {
        log.info("Updating team with ID: {}", id);

        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));

        team.setName(request.getName());
        team.setCity(request.getCity());

        if (request.getCoachId() != null) {
            Coach coach = coachRepository.findById(request.getCoachId())
                    .orElseThrow(() -> new ResourceNotFoundException("Coach not found"));
            team.setCoach(coach);
        }

        Team updatedTeam = teamRepository.save(team);
        log.info("Team updated: {}", id);
        return teamMapper.toResponse(updatedTeam);
    }

    @Transactional
    public void deleteTeam(Long id) {
        log.info("Deleting team with ID: {}", id);
        if (!teamRepository.existsById(id)) {
            throw new ResourceNotFoundException("Team not found");
        }
        teamRepository.deleteById(id);
        log.info("Team deleted (all players cascaded): {}", id);
    }
}
