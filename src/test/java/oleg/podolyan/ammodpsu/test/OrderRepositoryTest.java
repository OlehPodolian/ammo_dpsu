package oleg.podolyan.ammodpsu.test;

import oleg.podolyan.ammodpsu.domain.libs.Rank;
import oleg.podolyan.ammodpsu.domain.orders.Order;
import oleg.podolyan.ammodpsu.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    private Order orderFirst;

    @Before
    public void setUp() {
        orderFirst = getTestOrder("# 10001", "Description #1", "Title #1", true);
        entityManager.persist(orderFirst);
        entityManager.flush();
        orderFirst = orderRepository.findByTitle(orderFirst.getTitle());

        Order orderSecond = getTestOrder("# 10002", "Description #2", "Title #2", true);
        entityManager.persist(orderSecond);
        entityManager.flush();

        Order orderThird = getTestOrder("# 10003", "Description #3", "Title #4", false);
        entityManager.persist(orderThird);
        entityManager.flush();

        Order orderFourth = getTestOrder("# 10004", "Description #4", "Title #4", false);
        entityManager.persist(orderFourth);
        entityManager.flush();
    }

    @Test
    public void whenFindAll_thenSizeIsFour() {
        assertEquals(4, orderRepository.findAll().size());
    }

    @Test
    public void whenFindAllReceived_thenSizeIsTwo() {
        assertEquals(2, orderRepository.findAllByReceived(true).size());
    }

    @Test
    public void whenFindAllNotReceived_thenSizeIsTwo() {
        assertEquals(2, orderRepository.findAllByReceived(false).size());
    }

    @Test
    public void whenSoftDelete_thenSizeIsSame() {
        orderRepository.delete(orderFirst.getId());
        assertEquals(4, orderRepository.findAll().size());
    }

    @Test
    public void whenFindAllUnDeleted_thenSizeIsLess() {
        orderRepository.delete(orderFirst.getId());
        assertEquals(3, orderRepository.findAllByDeletedIsFalse().size());
    }

    @Test
    public void whenFindByTitle_thenReturnOrder() {
        Optional<Order> found = Optional.of(orderRepository.findByTitle(orderFirst.getTitle()));
        assertTrue(found.isPresent());
        assertEquals(orderFirst.getDescription(), found.get().getDescription());
    }

    @Test
    public void whenFindByNumber_thenReturnOrder() {
        Optional<Order> found = Optional.of(orderRepository.findByNumber(orderFirst.getNumber()));
        assertTrue(found.isPresent());
        assertEquals(orderFirst.getDescription(), found.get().getDescription());
    }

    @Test
    public void whenFindById_thenReturnOrder() {
        Optional<Order> found = Optional.of(orderRepository.findOne(orderFirst.getId()));
        assertTrue(found.isPresent());
        assertEquals(orderFirst.getDescription(), found.get().getDescription());
    }

    @Test
    public void whenUpdateSaved_thenSizeIsSame() {
        orderRepository.save(orderFirst);
        assertEquals(4, orderRepository.findAll().size());
    }

    private Order getTestOrder(String number, String description, String title, boolean isReceived){
        Order order = Order
                .builder()
                .date(LocalDate.now())
                .number(number)
                .description(description)
                .title(title)
                .received(isReceived)
                .build();
        return order;
    }

}
