package com.yourname.player_management.mapper;

import com.yourname.player_management.dto.TeamRequest;
import com.yourname.player_management.dto.TeamResponse;
import com.yourname.player_management.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeamMapper {

    private final PlayerMapper playerMapper;

    public TeamResponse toResponse(Team team) {
        return TeamResponse.builder()
                .id(team.getId())
                .name(team.getName())
                .city(team.getCity())
                .coachName(team.getCoach() != null ? team.getCoach().getName() : null)
                .playerCount(team.getPlayers() != null ? team.getPlayers().size() : 0)
                .build();
    }

    public TeamResponse toResponseWithPlayers(Team team) {
        TeamResponse response = toResponse(team);
        if (team.getPlayers() != null) {
            response.setPlayers(
                    team.getPlayers().stream()
                            .map(playerMapper::toResponse)
                            .collect(Collectors.toList())
            );
        }
        return response;
    }

    public Team toEntity(TeamRequest request) {
        return Team.builder()
                .name(request.getName())
                .city(request.getCity())
                .build();
    }
}
