package oleg.podolyan.ammodpsu.repository.military;

import oleg.podolyan.ammodpsu.domain.military.Department;
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
public class DepartmentRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private DepartmentRepository departmentRepository;

	private Department mainDepartment;

	private Department innerDepartment1;

	@Before
	public void setUp() throws Exception {
		mainDepartment = new Department();
		mainDepartment.setName("CommanderUnit");
		entityManager.persist(mainDepartment);
		entityManager.flush();
	}

	@Test
	public void whenFindByName_thenReturnDepartment() {
		Department found = departmentRepository.findByName(mainDepartment.getName());
		assertEquals(found.getId(), mainDepartment.getId());
	}

	@Test
	public void whenFindByNameFail_thenReturnNull() {
		assertNull(departmentRepository.findByName("CUS"));
	}

	@Test
	public void whenFndAll_thenReturnNotEmptyList() {
		assertTrue(departmentRepository.count() > 0);
	}

	@Test
	public void whenIsRootDepartment_thenParentIdIsNull(){
		Department found = departmentRepository.findByName(mainDepartment.getName());
		assertNull(found.getParentId());
	}

	@Test
	public void whenFirstLevelNestedDepartment_thenParentIdIsNotNull(){
		Department found = departmentRepository.findByName(mainDepartment.getName());

		Department savedDepartment = new Department();
		savedDepartment.setName("CUS");
		savedDepartment = departmentRepository.save(savedDepartment);
		assertNotNull(savedDepartment.getId());
		found.addDepartment(savedDepartment);
		assertNotNull(savedDepartment.getParentId());
		assertFalse(found.getDepartments().isEmpty());
	}

	@Test
	public void whenSecondLevelNestedDepartment_thenMiddleDepartmentHasSubDepartments(){
		Department found = departmentRepository.findByName(mainDepartment.getName());

		Department savedDepartment = new Department();
		savedDepartment.setName("CUS");
		savedDepartment = departmentRepository.save(savedDepartment);
		assertNotNull(savedDepartment.getId());
		found.addDepartment(savedDepartment);
		departmentRepository.save(found);

		Department deepDep = new Department();
		deepDep.setName("Dispatcher");
		deepDep = departmentRepository.save(deepDep);

		savedDepartment.addDepartment(deepDep);
		departmentRepository.save(savedDepartment);
		assertNotNull(deepDep.getParentId());
		assertFalse(found.getDepartments().iterator().next().getDepartments().isEmpty());
	}
}