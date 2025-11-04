package com.yourname.player_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsResponse {
    private Long id;
    private Long playerId;
    private String playerName;
    private Integer matchesPlayed;
    private Integer goalsScored;
    private Integer assists;
    private Integer yellowCards;
    private Integer redCards;
    private Integer minutesPlayed;
}
