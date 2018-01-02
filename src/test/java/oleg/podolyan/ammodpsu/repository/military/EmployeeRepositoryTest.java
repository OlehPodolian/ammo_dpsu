package oleg.podolyan.ammodpsu.repository.military;

import oleg.podolyan.ammodpsu.EntityBuilder;
import oleg.podolyan.ammodpsu.domain.military.Employee;
import oleg.podolyan.ammodpsu.domain.military.Soldier;
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
public class EmployeeRepositoryTest {
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	private Employee employee;

	@Before
	public void setUp() throws Exception {
		employee = EntityBuilder.buildEmployee("Jane");
		entityManager.persist(employee);
		entityManager.flush();
	}

	@Test
	public void whenFindByName_thenReturnSoldier(){
		Employee found = employeeRepository.findByUsername(employee.getUsername());
		assertEquals(found.getId(), employee.getId());
	}

	@Test
	public void whenCheckIfUsernameExistsYes_thenReturnTrue(){
		assertTrue(employeeRepository.existsByUsername(employee.getUsername()));
	}

	@Test
	public void whenCheckIfUsernameExistsNo_thenReturnFalse(){
		assertFalse(employeeRepository.existsByUsername("Peter"));
	}

	@Test
	public void whenFindSoldierByLastNameOk_thenListOfOneItem(){
		assertEquals(employeeRepository.findByPersonalInfoLastName(employee.getPersonalInfo().getLastName()).size(), 1L);
	}

	@Test
	public void whenFindSoldierByLastNameFail_thenReturnEmptyList(){
		assertTrue(employeeRepository.findByPersonalInfoLastName("Petrov").isEmpty());
	}

	@Test
	public void whenFindAll_thenReturnNotEmptyList(){
		assertFalse(employeeRepository.findAll().isEmpty());
	}

	// TODO run test for inventoryItems

}