package racingcar.repository.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RacingCarDao {
    private final JdbcTemplate jdbcTemplate;

    public RacingCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void insert(Long gameId, String playerName, int playerPosition) {
        final String sql = "insert into RACING_CAR (player_name, player_position, game_id) values (?,?,?)";
        jdbcTemplate.update(sql, playerName, playerPosition, gameId);
    }
}