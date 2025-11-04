package com.yourname.player_management.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamResponse {
    private Long id;
    private String name;
    private String city;
    private String coachName;
    private Integer playerCount;
    private List<PlayerResponse> players;
}
