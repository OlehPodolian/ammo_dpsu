package oleg.podolyan.ammodpsu.service.impl;

import oleg.podolyan.ammodpsu.domain.security.Role;
import oleg.podolyan.ammodpsu.repository.RoleRepository;
import oleg.podolyan.ammodpsu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public boolean existsByName(String roleName) {
        return roleRepository.existsByName(roleName);
    }
}
