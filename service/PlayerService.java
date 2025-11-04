package com.yourname.player_management.service;

import com.yourname.player_management.dto.PlayerRequest;
import com.yourname.player_management.dto.PlayerResponse;
import com.yourname.player_management.entity.Player;
import com.yourname.player_management.entity.Statistics;
import com.yourname.player_management.entity.Team;
import com.yourname.player_management.exception.ResourceNotFoundException;
import com.yourname.player_management.mapper.PlayerMapper;
import com.yourname.player_management.repository.PlayerRepository;
import com.yourname.player_management.repository.StatisticsRepository;
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
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final StatisticsRepository statisticsRepository;
    private final PlayerMapper playerMapper;

    @Transactional
    public PlayerResponse createPlayer(PlayerRequest request) {
        log.info("Creating player: {} {}", request.getFirstName(), request.getLastName());

        Team team = null;
        if (request.getTeamId() != null) {
            team = teamRepository.findById(request.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException("Team not found"));
        }

        Player player = playerMapper.toEntity(request, team);
        Player savedPlayer = playerRepository.save(player);

        // Create statistics for player
        Statistics statistics = Statistics.builder()
                .player(savedPlayer)
                .build();
        statisticsRepository.save(statistics);

        log.info("Player created with ID: {}", savedPlayer.getId());
        return playerMapper.toResponse(savedPlayer);
    }

    public PlayerResponse getPlayerById(Long id) {
        log.info("Fetching player with ID: {}", id);
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found"));
        return playerMapper.toResponse(player);
    }

    public List<PlayerResponse> getAllPlayers() {
        log.info("Fetching all players");
        return playerRepository.findAll().stream()
                .map(playerMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<PlayerResponse> getPlayersByTeam(Long teamId) {
        log.info("Fetching players for team ID: {}", teamId);
        return playerRepository.findByTeamId(teamId).stream()
                .map(playerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public PlayerResponse updatePlayer(Long id, PlayerRequest request) {
        log.info("Updating player with ID: {}", id);

        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found"));

        Team team = null;
        if (request.getTeamId() != null) {
            team = teamRepository.findById(request.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException("Team not found"));
        }

        player.setFirstName(request.getFirstName());
        player.setLastName(request.getLastName());
        player.setEmail(request.getEmail());
        player.setDateOfBirth(request.getDateOfBirth());
        player.setNationality(request.getNationality());
        player.setPosition(request.getPosition());
        player.setJerseyNumber(request.getJerseyNumber());
        player.setStatus(request.getStatus());
        player.setTeam(team);

        Player updatedPlayer = playerRepository.save(player);
        log.info("Player updated: {}", id);
        return playerMapper.toResponse(updatedPlayer);
    }

    @Transactional
    public void deletePlayer(Long id) {
        log.info("Deleting player with ID: {}", id);
        if (!playerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Player not found");
        }
        playerRepository.deleteById(id);
        log.info("Player deleted: {}", id);
    }
}
