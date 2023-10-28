package ee.tarvi.hiloproovit.repository;

import ee.tarvi.hiloproovit.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
