package oleg.podolyan.ammodpsu.repository.warehouse;

import oleg.podolyan.ammodpsu.domain.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

	Warehouse findByName(String name);
}
