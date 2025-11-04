package com.yourname.player_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoachRequest {
    private String name;
    private String nationality;
    private String specialty;
    private Integer experience;
}
