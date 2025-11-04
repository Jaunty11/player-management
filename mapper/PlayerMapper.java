package com.yourname.player_management.mapper;

import com.yourname.player_management.dto.PlayerRequest;
import com.yourname.player_management.dto.PlayerResponse;
import com.yourname.player_management.entity.Player;
import com.yourname.player_management.entity.Team;
import com.yourname.player_management.enums.PlayerStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class PlayerMapper {

    public PlayerResponse toResponse(Player player) {
        return PlayerResponse.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .fullName(player.getFirstName() + " " + player.getLastName())
                .email(player.getEmail())
                .age(calculateAge(player.getDateOfBirth()))
                .nationality(player.getNationality())
                .position(player.getPosition())
                .jerseyNumber(player.getJerseyNumber())
                .status(player.getStatus())
                .teamId(player.getTeam() != null ? player.getTeam().getId() : null)
                .teamName(player.getTeam() != null ? player.getTeam().getName() : null)
                .matchesPlayed(player.getStatistics() != null ? player.getStatistics().getMatchesPlayed() : 0)
                .goalsScored(player.getStatistics() != null ? player.getStatistics().getGoalsScored() : 0)
                .build();
    }

    public Player toEntity(PlayerRequest request, Team team) {
        return Player.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .nationality(request.getNationality())
                .position(request.getPosition())
                .jerseyNumber(request.getJerseyNumber())
                .status(request.getStatus() != null ? request.getStatus() : PlayerStatus.ACTIVE)
                .team(team)
                .build();
    }

    private Integer calculateAge(LocalDate dob) {
        return dob != null ? Period.between(dob, LocalDate.now()).getYears() : null;
    }
}
