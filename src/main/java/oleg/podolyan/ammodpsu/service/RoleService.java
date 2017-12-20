package oleg.podolyan.ammodpsu.service;

import oleg.podolyan.ammodpsu.domain.security.Role;

public interface RoleService {

    Role findByName(String roleName);

    Role save(Role role);

    boolean existsByName(String roleName);
}
