package ee.mihkel.salat.repository;

// JpaRepository ---> siin on kõige rohkem funktsioone
// PaginationAndSortingRepository
// CrudRepository ---> siin on kõige vähem funktsioone

import ee.mihkel.salat.entity.Toiduaine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToiduaineRepository extends JpaRepository<Toiduaine, String> {
}
