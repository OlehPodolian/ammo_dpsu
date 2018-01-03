package oleg.podolyan.ammodpsu.repository.warehouse;

import oleg.podolyan.ammodpsu.EntityBuilder;
import oleg.podolyan.ammodpsu.domain.report.Account;
import oleg.podolyan.ammodpsu.domain.report.AccountWarehouse;
import oleg.podolyan.ammodpsu.domain.user.User;
import oleg.podolyan.ammodpsu.domain.warehouse.Warehouse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WarehouseRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private WarehouseRepository warehouseRepository;

	private Warehouse warehouse;

	private User manger;

	private Account account;

	@Before
	public void setUp() throws Exception {
		warehouse = EntityBuilder.buildWarehouse("Warehouse #1");
		entityManager.persist(warehouse);

		manger = EntityBuilder.buildUser("Volodya");
		entityManager.persist(manger);

		warehouse.getManagers().add(manger);

		account = new Account("Account # 2");
		entityManager.persist(account);

		warehouse.getAccountWarehouses().add(new AccountWarehouse(account, warehouse));

		entityManager.flush();
	}

	@Test
	public void whenFindByName_thenReturnWarehouse() {
		Warehouse found = warehouseRepository.findByName(warehouse.getName());
		assertEquals(found.getId(), warehouse.getId());
	}

	@Test(expected = NullPointerException.class)
	public void whenFindByNameFail_thenReturnNull() {
		Warehouse found = warehouseRepository.findByName("warehouse.getName()");
		assertNull(found);
		found.getName();
	}

	@Test
	public void whenFindAll_thenReturnNotEmptyList() {
		assertFalse(warehouseRepository.findAll().isEmpty());
	}

	@Test
	public void whenGetManagers_thenReturnNotEmptyList() {
		Warehouse found = warehouseRepository.findByName(warehouse.getName());
		entityManager.refresh(found);
		assertTrue(found.getManagers().size() > 0);
	}

	@Test
	public void whenGetAccounts_thenReturnNotEmptyList() {
		Warehouse found = warehouseRepository.findByName(warehouse.getName());
		entityManager.refresh(found);
		assertEquals(found.getAccountWarehouses().iterator().next().getAccount().getName(), account.getName());
	}

	@Test
	public void whenCheckIfManagerOk_thenReturnTrue() {
		Warehouse found = warehouseRepository.findByName(warehouse.getName());
		entityManager.refresh(found);
		assertTrue(found.getManagers().contains(manger));
	}

	@Test
	public void whenCheckIfManagerFail_thenReturnFalse() {
		Warehouse found = warehouseRepository.findByName(warehouse.getName());
		entityManager.refresh(found);
		User bembi = new User();
		bembi.setId(34L);
		bembi.setUsername("Elizabeth");
		assertFalse(found.getManagers().contains(bembi));
	}

	@Test
	public void whenCheckIfManagerByUsernameOk_thenReturnTrue() {
		assertTrue(warehouseRepository.existsByManagersUsername(manger.getUsername()));
	}

	@Test
	public void whenCheckIfManagerByUsernameFail_thenReturnFalse() {
		assertFalse(warehouseRepository.existsByManagersUsername("manger.getUsername()"));
	}

	@Test
	public void whenSoftDeleteWarehouse_thenReturnItStoredButSetDeleted() {
		Warehouse found = warehouseRepository.findByName(warehouse.getName());
		warehouseRepository.delete(found);
		found = warehouseRepository.findByName(warehouse.getName());
		assertTrue(warehouseRepository.count() > 0);
		assertTrue(found.isDeleted());
	}

	@Test
	public void whenGetNotDeleteWarehouses_thenReturnEmptyList() {
		Warehouse found = warehouseRepository.findByName(warehouse.getName());
		warehouseRepository.delete(found);
		assertTrue(warehouseRepository.findAllByDeletedIsFalse().isEmpty());
	}
}