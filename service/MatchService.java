package com.yourname.player_management.service;

import com.yourname.player_management.dto.MatchRequest;
import com.yourname.player_management.dto.MatchResponse;
import com.yourname.player_management.entity.Match;
import com.yourname.player_management.exception.ResourceNotFoundException;
import com.yourname.player_management.mapper.MatchMapper;
import com.yourname.player_management.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    @Transactional
    public MatchResponse createMatch(MatchRequest request) {
        log.info("Creating match: {} vs {}", request.getOpponent(), request.getVenue());
        Match match = matchMapper.toEntity(request);
        Match savedMatch = matchRepository.save(match);
        log.info("Match created with ID: {}", savedMatch.getId());
        return matchMapper.toResponse(savedMatch);
    }

    public MatchResponse getMatchById(Long id) {
        log.info("Fetching match with ID: {}", id);
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found"));
        return matchMapper.toResponse(match);
    }

    public List<MatchResponse> getAllMatches() {
        log.info("Fetching all matches");
        return matchRepository.findAll().stream()
                .map(matchMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMatch(Long id) {
        log.info("Deleting match with ID: {}", id);
        if (!matchRepository.existsById(id)) {
            throw new ResourceNotFoundException("Match not found");
        }
        matchRepository.deleteById(id);
        log.info("Match deleted: {}", id);
    }
}
