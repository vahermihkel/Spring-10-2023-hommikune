package ee.tarvi.hiloproovit.repository;

import ee.tarvi.hiloproovit.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
