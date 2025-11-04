package com.yourname.player_management.dto;

import com.yourname.player_management.enums.Position;
import com.yourname.player_management.enums.PlayerStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private Integer age;
    private String nationality;
    private Position position;
    private Integer jerseyNumber;
    private PlayerStatus status;
    private Long teamId;
    private String teamName;
    private Integer matchesPlayed;
    private Integer goalsScored;
}
