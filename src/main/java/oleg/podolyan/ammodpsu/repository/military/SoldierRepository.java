package oleg.podolyan.ammodpsu.repository.military;

import oleg.podolyan.ammodpsu.domain.military.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldierRepository extends JpaRepository<Soldier, Long> {

	Soldier findByUsername(String username);

	boolean existsByUsername(String username);

	List<Soldier> findByPersonalInfoLastName(String lastName);
}
