package oleg.podolyan.ammodpsu.repository.warehouse;

import oleg.podolyan.ammodpsu.domain.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

	Warehouse findByName(String name);

	boolean existsByManagersUsername(String username);

	List<Warehouse> findAllByDeletedIsFalse();
}
