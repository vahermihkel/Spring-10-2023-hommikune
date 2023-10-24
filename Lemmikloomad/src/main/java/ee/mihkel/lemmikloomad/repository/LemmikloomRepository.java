package ee.mihkel.lemmikloomad.repository;

import ee.mihkel.lemmikloomad.entity.Lemmikloom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LemmikloomRepository extends JpaRepository<Lemmikloom, Integer> {
    // Lemmikloom     või   List<Lemmikloom>
    Lemmikloom findFirstByOrderByKaalDesc();

    List<Lemmikloom> findAllByKaalBetween(double min, double max);

    List<Lemmikloom> findAllByKaalGreaterThan(double min);

    List<Lemmikloom> findAllByKaalGreaterThanAndNimetusEquals(double kaal, String nimetus);
}
