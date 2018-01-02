package oleg.podolyan.ammodpsu.repository.user;

import oleg.podolyan.ammodpsu.domain.user.User;
import oleg.podolyan.ammodpsu.domain.user.security.Role;
import oleg.podolyan.ammodpsu.domain.user.security.RoleType;
import oleg.podolyan.ammodpsu.domain.user.security.UserRole;
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
public class UserRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	private User user;

	@Before
	public void setUp() throws Exception {
		entityManager.persist(new Role(RoleType.ADMIN.getValue()));
		entityManager.flush();
		user = getUser("oleg");
		entityManager.persist(user);
		entityManager.flush();
	}

	@Test
	public void whenFindByUsername_thenReturnUsername() {
		User found = userRepository.findByUsername(user.getUsername());
		assertEquals(found.getFatherName(), user.getFatherName());
	}

	@Test
	public void whenFindAll_thenReturnOne() {
		assertEquals(userRepository.findAll().size(), 1);
	}

	private User getUser(String username){
		User user = new User();
		user.setUsername(username);
		user.setPassword("pass");
		user.setLastName("Podolyan");
		user.setFirstName("Oleg");
		user.setFatherName("Ivanovich");
		user.setPhoneNumber("0672989761");
		user.setPosition("I don't know who I am");

		user.getUserRoles().add(new UserRole(user, roleRepository.findByName(RoleType.ADMIN.getValue())));
		return user;
	}
}