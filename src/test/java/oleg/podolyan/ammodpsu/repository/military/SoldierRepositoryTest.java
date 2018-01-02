package oleg.podolyan.ammodpsu.repository.military;

import oleg.podolyan.ammodpsu.EntityBuilder;
import oleg.podolyan.ammodpsu.domain.military.Soldier;
import oleg.podolyan.ammodpsu.repository.user.UserRepository;
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
public class SoldierRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private SoldierRepository soldierRepository;

	@Autowired
	private UserRepository userRepository;

	private Soldier soldier;

	@Before
	public void setUp() throws Exception {
		soldier = EntityBuilder.buildSoldier("Jane");
		entityManager.persist(soldier);
		entityManager.flush();
	}

	@Test
	public void whenFindByName_thenReturnSoldier(){
		Soldier found = soldierRepository.findByUsername(soldier.getUsername());
		assertEquals(found.getId(), soldier.getId());
	}

	@Test
	public void whenCheckIfUsernameExistsYes_thenReturnTrue(){
		assertTrue(soldierRepository.existsByUsername(soldier.getUsername()));
	}

	@Test
	public void whenCheckIfUsernameExistsNo_thenReturnFalse(){
		assertFalse(soldierRepository.existsByUsername("Peter"));
	}

	@Test
	public void whenFindSoldierByLastNameOk_thenListOfOneItem(){
		assertEquals(soldierRepository.findByPersonalInfoLastName(soldier.getPersonalInfo().getLastName()).size(), 1L);
	}

	@Test
	public void whenFindSoldierByLastNameFail_thenReturnEmptyList(){
		assertTrue(soldierRepository.findByPersonalInfoLastName("Petrov").isEmpty());
	}

	@Test
	public void whenFindAll_thenReturnNotEmptyList(){
		assertFalse(soldierRepository.findAll().isEmpty());
	}

	// TODO run test for inventoryItems & issuedItems


}