package com.yourname.player_management.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchRequest {
    private LocalDate matchDate;
    private String opponent;
    private String venue;
    private Integer homeScore;
    private Integer awayScore;
    private String status;
}
