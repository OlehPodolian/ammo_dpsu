package oleg.podolyan.ammodpsu.repository.user;

import oleg.podolyan.ammodpsu.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	@Query(value = "select u from User u where u.enabled = true")
	List<User> findAll();

}
