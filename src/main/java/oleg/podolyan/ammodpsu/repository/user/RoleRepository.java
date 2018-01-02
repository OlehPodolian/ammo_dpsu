package oleg.podolyan.ammodpsu.repository.user;

import oleg.podolyan.ammodpsu.domain.user.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String roleName);

	boolean existsByName(String roleName);

	List<Role> findAll();

}
