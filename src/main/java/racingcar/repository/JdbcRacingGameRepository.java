package racingcar.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.entity.GameHistoryEntity;
import racingcar.repository.dao.PlayResultDao;
import racingcar.repository.dao.RacingCarDao;

@Repository
public class JdbcRacingGameRepository implements RacingGameRepository {

    private final PlayResultDao playResultDao;
    private final RacingCarDao racingCarDao;

    public JdbcRacingGameRepository(final PlayResultDao playResultDao, final RacingCarDao racingCarDao) {
        this.playResultDao = playResultDao;
        this.racingCarDao = racingCarDao;
    }

    @Override
    public List<GameHistoryEntity> findGameHistories() {
        return playResultDao.findGameHistories();
    }

    @Override
    public void saveGameResult(final Cars cars, final TryCount tryCount) {
        Long gameId = playResultDao.insertWithKeyHolder(tryCount.getValue(), cars.getWinner());
        for (Car car : cars.getCars()) {
            racingCarDao.insert(gameId, car.getName().getValue(), car.getDistance().getValue());
        }
    }
}