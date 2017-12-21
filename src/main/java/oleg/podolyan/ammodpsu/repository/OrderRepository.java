package oleg.podolyan.ammodpsu.repository;

import oleg.podolyan.ammodpsu.domain.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Override
	Order findOne(Long id);

	Order findByTitle(String title);

	Order findByNumber(String orderNumber);

	List<Order> findAll();

	List<Order> findAllByDeletedIsFalse();

	List<Order> findAllByReceived(boolean received);

	void deleteBy(Long id);

	void delete(Order order);
}
