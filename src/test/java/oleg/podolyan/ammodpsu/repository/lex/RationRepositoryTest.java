package oleg.podolyan.ammodpsu.repository.lex;

import oleg.podolyan.ammodpsu.EntityBuilder;
import oleg.podolyan.ammodpsu.domain.lex.Ration;
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
public class RationRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private RationRepository rationRepository;

	private Ration ration;
	@Before
	public void setUp() throws Exception {
		ration = EntityBuilder.buildRation("Норма № 2", "забезпечення речовим майном військовослужбовців чоловічої статі, " +
				"які проходять військову службу за контрактом, та офіцерського складу, який проходить кадрову " +
				"військову службу (крім Морської охорони)");
		entityManager.persist(ration);
		entityManager.flush();
	}

	@Test
	public void whenFindByName_thenReturnRation() {
		Ration found = rationRepository.findByName(ration.getName());
		assertEquals(found.getName(), ration.getName());
	}

	@Test
	public void whenDeleted_thenOnlySetDeleted() {
		Ration found = rationRepository.findByName(ration.getName());
		assertFalse(found.isDeleted());
		rationRepository.delete(found);
		assertFalse(rationRepository.findAll().isEmpty());
		found = rationRepository.findOne(found.getId());
		assertTrue(found.isDeleted());
	}

	@Test
	public void whenFindAllByDeletedIsFalse_thenReturnEmptyList(){
		Ration found = rationRepository.findByName(ration.getName());
		assertFalse(found.isDeleted());
		rationRepository.delete(found);
		assertTrue(rationRepository.findAllByDeletedIsFalse().isEmpty());
	}
}