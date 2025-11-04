package com.yourname.player_management.mapper;

import com.yourname.player_management.dto.MatchRequest;
import com.yourname.player_management.dto.MatchResponse;
import com.yourname.player_management.entity.Match;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {

    public MatchResponse toResponse(Match match) {
        return MatchResponse.builder()
                .id(match.getId())
                .matchDate(match.getMatchDate())
                .opponent(match.getOpponent())
                .venue(match.getVenue())
                .homeScore(match.getHomeScore())
                .awayScore(match.getAwayScore())
                .status(match.getStatus())
                .playerCount(match.getPlayers() != null ? match.getPlayers().size() : 0)
                .build();
    }

    public Match toEntity(MatchRequest request) {
        return Match.builder()
                .matchDate(request.getMatchDate())
                .opponent(request.getOpponent())
                .venue(request.getVenue())
                .homeScore(request.getHomeScore())
                .awayScore(request.getAwayScore())
                .status(request.getStatus())
                .build();
    }
}
