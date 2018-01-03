package oleg.podolyan.ammodpsu.repository.lex;

import oleg.podolyan.ammodpsu.EntityBuilder;
import oleg.podolyan.ammodpsu.domain.lex.ClothesType;
import oleg.podolyan.ammodpsu.domain.lex.Ration;
import oleg.podolyan.ammodpsu.domain.lex.RationItem;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RationItemRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private RationItemRepository rationItemRepository;

	@Autowired
	private RationRepository rationRepository;

	private Ration ration1, ration2;

	private RationItem rationItem1, rationItem2;
	@Before
	public void setUp() throws Exception {
		ration1 = EntityBuilder.buildRation("Норма № 2", "забезпечення речовим майном військовослужбовців чоловічої статі, " +
				"які проходять військову службу за контрактом, та офіцерського складу, який проходить кадрову " +
				"військову службу (крім Морської охорони)");
		entityManager.persist(ration1);

		rationItem1 = EntityBuilder.buildRationItem(ClothesType.UNIFORM, "Костюм літній польовий", "");
		entityManager.persist(rationItem1);

		rationItem2 = EntityBuilder.buildRationItem(ClothesType.FOOTWEAR, "Напівчоботи утеплені", "");
		entityManager.persist(rationItem2);

		ration1.addRationItem(rationItem1);
		ration1.addRationItem(rationItem2);

		ration2 = EntityBuilder.buildRation("Норма № 3", "забезпечення речовим майном військовослужбовців чоловічої статі, " +
				"які проходять (крім Морської охорони)");
		entityManager.persist(ration2);
		entityManager.flush();
	}

	@Test
	public void whenDeleted_thenOnlySetDeleted() {
		RationItem found = rationItemRepository.findOne(rationItem1.getId());
		assertFalse(found.isDeleted());
		rationItemRepository.delete(found);
		assertFalse(rationItemRepository.findAll().isEmpty());
		found = rationItemRepository.findOne(found.getId());
		assertTrue(found.isDeleted());
	}

	@Test(expected = NullPointerException.class)
	public void whenFindByIdFails_thenReturnNull(){
		RationItem found = rationItemRepository.findOne(55L);
		assertNull(found);
		found.getDetails(); // NULL
	}

	@Test
	public void whenRationGetRationItems_thenReturnSize(){
		Ration found1 = rationRepository.findByName(ration1.getName());
		assertEquals(found1.getRationItems().size(), 2);
		Ration found2 = rationRepository.findByName(ration2.getName());
		assertTrue(found2.getRationItems().isEmpty());
	}

	@Test
	public void whenFindAllByDeletedIsFalse_thenReturnListWithSingleItem(){
		RationItem found = rationItemRepository.findByRationAndName(ration1, rationItem1.getName());
		assertFalse(found.isDeleted());
		rationItemRepository.delete(found);
		found = rationItemRepository.findByRationAndName(ration1, rationItem1.getName());
		assertTrue(found.isDeleted());
		assertEquals(rationItemRepository.findAllByDeletedIsFalse().size(), 1);
	}

	@Test
	public void whenDeleteParentRation_thenChildrenItemsHasNullAsParent(){
		rationRepository.delete(ration1);
		Ration foundR = rationRepository.findByName(ration1.getName());
		assertTrue(foundR.isDeleted());
		assertEquals(rationItemRepository.count(), 2);
		RationItem found = rationItemRepository.findOne(rationItem1.getId());
		entityManager.refresh(found);
		assertNull(found.getRation());
	}
}