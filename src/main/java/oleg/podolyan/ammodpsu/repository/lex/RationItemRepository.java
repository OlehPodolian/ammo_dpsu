package oleg.podolyan.ammodpsu.repository.lex;

import oleg.podolyan.ammodpsu.domain.lex.Ration;
import oleg.podolyan.ammodpsu.domain.lex.RationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RationItemRepository extends JpaRepository<RationItem, Long> {

	List<RationItem> findAllByDeletedIsFalse();

	List<RationItem> findAllByRation(Ration ration);

	RationItem findByRationAndName(Ration ration, String itemName);

	void delete(RationItem rationItem);

}
