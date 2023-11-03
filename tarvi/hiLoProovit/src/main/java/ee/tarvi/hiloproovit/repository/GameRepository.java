package ee.tarvi.hiloproovit.repository;

import ee.tarvi.hiloproovit.entity.Game;
import ee.tarvi.hiloproovit.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    // Game   või   List<Game>

    List<Game> findAllByOrderByPointsDesc();

    List<Game> findAllByPlayerName(String name);

    List<Game> findAllByPlayerNameOrderByPointsDesc(String name);

    List<Game> findAllByPointsGreaterThanEqual(int points);

    Game findFirstByOrderByPointsDesc();

}
