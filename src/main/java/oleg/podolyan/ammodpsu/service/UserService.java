package oleg.podolyan.ammodpsu.service;

import oleg.podolyan.ammodpsu.domain.security.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    User save(User user);

    List<User> findAll();

    void delete(User user);

    void deleteByUsername(String username);
}
