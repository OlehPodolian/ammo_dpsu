package oleg.podolyan.ammodpsu.service;

import oleg.podolyan.ammodpsu.domain.user.security.Role;

import java.util.List;

public interface RoleService {

	boolean exists(String roleName);

	Role save(Role role);

	Role find(String roleName);

	List<Role> findAll();
}
