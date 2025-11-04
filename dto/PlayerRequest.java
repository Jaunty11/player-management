package com.yourname.player_management.dto;

import com.yourname.player_management.enums.Position;
import com.yourname.player_management.enums.PlayerStatus;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email")
    private String email;

    private LocalDate dateOfBirth;
    private String nationality;

    @NotNull(message = "Position is required")
    private Position position;

    @Min(1)
    @Max(99)
    private Integer jerseyNumber;

    private PlayerStatus status;
    private Long teamId;
}
