package com.yourname.player_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "player_id", unique = true, nullable = false)
    private Player player;

    @Column(name = "matches_played")
    @Builder.Default
    private Integer matchesPlayed = 0;

    @Column(name = "goals_scored")
    @Builder.Default
    private Integer goalsScored = 0;

    @Builder.Default
    private Integer assists = 0;

    @Column(name = "yellow_cards")
    @Builder.Default
    private Integer yellowCards = 0;

    @Column(name = "red_cards")
    @Builder.Default
    private Integer redCards = 0;

    @Column(name = "minutes_played")
    @Builder.Default
    private Integer minutesPlayed = 0;
}
