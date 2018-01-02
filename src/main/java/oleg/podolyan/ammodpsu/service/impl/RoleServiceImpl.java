package oleg.podolyan.ammodpsu.service.impl;

import oleg.podolyan.ammodpsu.domain.user.security.Role;
import oleg.podolyan.ammodpsu.repository.user.RoleRepository;
import oleg.podolyan.ammodpsu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public boolean exists(String roleName) {
		return roleRepository.existsByName(roleName);
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role find(String roleName) {
		return roleRepository.findByName(roleName);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
}
