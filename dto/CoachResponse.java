package com.yourname.player_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoachResponse {
    private Long id;
    private String name;
    private String nationality;
    private String specialty;
    private Integer experience;
    private String teamName;
}
