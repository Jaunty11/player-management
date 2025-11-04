package com.yourname.player_management.mapper;

import com.yourname.player_management.dto.CoachRequest;
import com.yourname.player_management.dto.CoachResponse;
import com.yourname.player_management.entity.Coach;
import org.springframework.stereotype.Component;

@Component
public class CoachMapper {

    public CoachResponse toResponse(Coach coach) {
        return CoachResponse.builder()
                .id(coach.getId())
                .name(coach.getName())
                .nationality(coach.getNationality())
                .specialty(coach.getSpecialty())
                .experience(coach.getExperience())
                .teamName(coach.getTeam() != null ? coach.getTeam().getName() : null)
                .build();
    }

    public Coach toEntity(CoachRequest request) {
        return Coach.builder()
                .name(request.getName())
                .nationality(request.getNationality())
                .specialty(request.getSpecialty())
                .experience(request.getExperience())
                .build();
    }
}
