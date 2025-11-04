package com.yourname.player_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamRequest {
    private String name;
    private String city;
    private Long coachId;
}
