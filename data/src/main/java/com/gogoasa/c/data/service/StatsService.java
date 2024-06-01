package com.gogoasa.c.data.service;


import com.gogoasa.c.data.model.Stats;
import com.gogoasa.c.data.model.User;
import com.gogoasa.c.data.model.dto.UserResponseDto;
import com.gogoasa.c.data.model.dto.UserStatsDto;
import com.gogoasa.c.data.repository.StatsRepository;
import com.gogoasa.c.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StatsService {
    private final StatsRepository statsRepository;

    private final UserRepository userRepository;

    public void increaseNumberOfSearches(String username) {

        User user = userRepository.findById(username).orElseThrow();

        Optional<Stats> stats = statsRepository.findStatsByUser(user);

        stats.ifPresentOrElse(
                s -> {
                    s.setNumberOfSearches(s.getNumberOfSearches() + 1);
                    statsRepository.save(s);
                },
                () -> {
                    Stats newStats = new Stats();
                    newStats.setUser(user);
                    newStats.setNumberOfSearches(1);
                    statsRepository.save(newStats);
                }
        );
    }

    public UserStatsDto getUserStats(String username) {
        User user = userRepository.findById(username).orElseThrow();

        Optional<Stats> stats = statsRepository.findStatsByUser(user);

        if (stats.isEmpty()){
            return new UserStatsDto(
               username,
               0,
               100.0
            );
        }

        Double topPercent = statsRepository.findUserPercentile(username);

        return new UserStatsDto(
            username,
            stats.get().getNumberOfSearches(),
            topPercent
        );
    }
}
