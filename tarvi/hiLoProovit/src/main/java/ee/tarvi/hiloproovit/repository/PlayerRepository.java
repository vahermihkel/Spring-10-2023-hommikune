package ee.tarvi.hiloproovit.repository;

import ee.tarvi.hiloproovit.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, String> {
    // Player    või   List<Player>

    List<Player> findAllByOrderByHighScoreDesc();

    Player findFirstByOrderByHighScoreDesc();
}
