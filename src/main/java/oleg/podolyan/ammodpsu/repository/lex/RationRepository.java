package oleg.podolyan.ammodpsu.repository.lex;

import oleg.podolyan.ammodpsu.domain.lex.Ration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RationRepository extends JpaRepository<Ration, Long> {

	Ration findByName(String name);

	List<Ration> findAllByDeletedIsFalse();
}
