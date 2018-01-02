package oleg.podolyan.ammodpsu.repository.user;

import oleg.podolyan.ammodpsu.domain.user.security.Role;
import oleg.podolyan.ammodpsu.domain.user.security.RoleType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private RoleRepository roleRepository;

	private Role roleUser;

	@Before
	public void setUp() throws Exception {
		roleUser = new Role(RoleType.ADMIN.toString());
		entityManager.persist(roleUser);
	}

	@Test
	public void whenFindByName_thenReturnRole() {
		Role found = roleRepository.findByName(RoleType.ADMIN.getValue());
		assertEquals(roleUser.getName(), found.getName());
	}

	@Test
	public void whenExistsByNameForPresent_returnTrue() {
		assertTrue(roleRepository.existsByName(RoleType.ADMIN.getValue()));
	}

	@Test
	public void whenExistsByNameForAbsent_returnFalse() {
		assertFalse(roleRepository.existsByName(RoleType.MANAGER.getValue()));
	}

	@Test
	public void findAll() {
	}
}