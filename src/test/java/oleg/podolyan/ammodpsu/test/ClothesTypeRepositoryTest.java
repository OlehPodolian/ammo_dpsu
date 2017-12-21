package oleg.podolyan.ammodpsu.test;

import oleg.podolyan.ammodpsu.domain.libs.ClothesType;
import oleg.podolyan.ammodpsu.repository.ClothesTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClothesTypeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClothesTypeRepository clothesTypeRepository;

    @Before
    public void prepare() {
        ClothesType shirt = new ClothesType("shirt".toUpperCase());
        shirt.getSizes().addAll(Arrays.asList("38", "39", "40", "41"));
        entityManager.persist(shirt);

        ClothesType shoes = new ClothesType("shoes".toUpperCase());
        shoes.getSizes().addAll(Arrays.asList("38", "39", "40", "41"));
        entityManager.persist(shoes);

        ClothesType jacket = new ClothesType("jacket".toUpperCase());
        jacket.getSizes().addAll(Arrays.asList("38", "39", "40", "41"));
        entityManager.persist(jacket);

        ClothesType cap = new ClothesType("cap".toUpperCase());
        cap.getSizes().addAll(Arrays.asList("38", "39", "40", "41"));
        entityManager.persist(cap);

        ClothesType other = new ClothesType("other".toUpperCase());
        other.getSizes().addAll(Arrays.asList("38", "39", "40", "41"));
        entityManager.persist(other);
        entityManager.flush();
    }

    @Test
    public void whenFindAll_thenContainsAllValues() {
        Set<String> values = new HashSet<>();
        values.addAll(Arrays.asList("shirt".toUpperCase(), "shoes".toUpperCase(),
                "jacket".toUpperCase(), "cap".toUpperCase(), "other".toUpperCase()));
        Set<String> stored = clothesTypeRepository
                .findAll()
                .stream()
                .map(ClothesType::getValue)
                .collect(Collectors.toSet());
        assertEquals(values, stored);
    }

    @Test
    public void whenFindAll_thenReturnFive() {
        assertEquals(clothesTypeRepository.findAll().size(), 5);
    }

    @Test
    public void whenFindByValue_thenReturnClothesType() {
        String expected = "cap".toUpperCase();
        Optional<ClothesType> actual = Optional.of(clothesTypeRepository.findOne(expected));

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get().getValue());
    }

    @Test
    public void whenDelete_thenReturnLessInOne() {
        int expected = clothesTypeRepository.findAll().size();
        clothesTypeRepository.delete("cap".toUpperCase());
        int actual = clothesTypeRepository.findAll().size();

        assertEquals(expected, actual + 1);
    }
}
