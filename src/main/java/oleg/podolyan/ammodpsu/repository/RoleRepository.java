package oleg.podolyan.ammodpsu.repository;

import oleg.podolyan.ammodpsu.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);

    Role save(Role role);

    boolean existsByName(String roleName);
}
