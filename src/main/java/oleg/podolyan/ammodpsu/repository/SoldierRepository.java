package oleg.podolyan.ammodpsu.repository;

import oleg.podolyan.ammodpsu.domain.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldierRepository extends JpaRepository<Soldier, Long> {

    Soldier findByUsername(String username);

    Soldier save(Soldier user);

    List<Soldier> findAll();

    void delete(Soldier soldier);

    void deleteByUsername(String username);

    boolean existsByUsername(String username);
}
