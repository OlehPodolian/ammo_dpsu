package oleg.podolyan.ammodpsu.repository;

import oleg.podolyan.ammodpsu.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User save(User user);

    List<User> findAll();

    void delete(User user);

    void deleteByUsername(String username);
}
