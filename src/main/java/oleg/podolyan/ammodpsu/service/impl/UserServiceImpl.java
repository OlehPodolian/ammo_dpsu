package oleg.podolyan.ammodpsu.service.impl;

import oleg.podolyan.ammodpsu.domain.security.User;
import oleg.podolyan.ammodpsu.repository.SoldierRepository;
import oleg.podolyan.ammodpsu.repository.UserRepository;
import oleg.podolyan.ammodpsu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SoldierRepository soldierRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SoldierRepository soldierRepository) {
        this.userRepository = userRepository;
        this.soldierRepository = soldierRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(User user) {
        if(soldierRepository.existsByUsername(user.getUsername())) {
            soldierRepository.deleteByUsername(user.getUsername());
        }
        userRepository.delete(user);
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }
}
