package oleg.podolyan.ammodpsu.repository.warehouse;

import oleg.podolyan.ammodpsu.EntityBuilder;
import oleg.podolyan.ammodpsu.domain.warehouse.Warehouse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.swing.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WarehouseRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private WarehouseRepository warehouseRepository;

	private Warehouse warehouse;

	@Before
	public void setUp() throws Exception {
		warehouse = EntityBuilder.buildWarehouse("Warehouse #1");
		entityManager.persist(warehouse);
		entityManager.flush();
	}

	@Test
	public void whenFindByName_thenReturnWarehouse() {
		Warehouse found = warehouseRepository.findByName(warehouse.getName());
		assertEquals(found.getId(), warehouse.getId());
	}
}