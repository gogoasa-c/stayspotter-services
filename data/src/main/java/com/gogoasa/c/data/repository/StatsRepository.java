package com.gogoasa.c.data.repository;

import com.gogoasa.c.data.model.Stats;
import com.gogoasa.c.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StatsRepository extends JpaRepository<Stats, Long> {
    Optional<Stats> findStatsByUser(User user);

    @Query(value = """
        SELECT percentile
        FROM (
            SELECT
                user_username,
                (CAST(RANK() OVER (ORDER BY number_of_searches DESC) as FLOAT) / COUNT(*) OVER () * 100) AS percentile
            FROM stats
        ) AS ranked_stats
        WHERE user_username = :username
        """, nativeQuery = true)
    Double findUserPercentile(@Param("username") String username);
}
