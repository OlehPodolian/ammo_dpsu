package oleg.podolyan.ammodpsu.test;

import oleg.podolyan.ammodpsu.domain.libs.ClothesType;
import oleg.podolyan.ammodpsu.domain.libs.Rank;
import oleg.podolyan.ammodpsu.repository.ClothesTypeRepository;
import oleg.podolyan.ammodpsu.repository.RankRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RankRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RankRepository rankRepository;

    final String [] ranks = {
            "Старший прапорщик",
            "Прапорщик",
            "Старшина",
            "Сержант",
            "Молодший сержант"
    };

    // asc
    @Before
    public void prepare() {
        for(int ordinal = 0; ordinal < ranks.length; ordinal++){
            int position = ranks.length - ( ordinal + 1);
            entityManager.persist(new Rank(ranks[position], ordinal));
        }
        entityManager.flush();
    }

    @Test
    public void whenFindAll_thenContainsAllValues() {
        Set<String> expected = new HashSet<>();
        expected.addAll(Arrays.asList(ranks));
        Set<String> actual = rankRepository
                .findAll()
                .stream()
                .map(Rank::getValue)
                .collect(Collectors.toSet());

        assertEquals(expected, actual);
    }

    @Test
    public void whenFindByValue_thenOrdinalIsSubsequent() {
        assertEquals(2, rankRepository.findOne(ranks[2]).getOrdinal());
    }

    @Test
    public void whenDelete_thenLessInOne() {
        rankRepository.delete(ranks[3]);
        assertEquals(ranks.length -1, rankRepository.findAll().size());
    }

}
