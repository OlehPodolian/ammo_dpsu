package oleg.podolyan.ammodpsu.service;

import oleg.podolyan.ammodpsu.domain.user.User;

public interface UserService {

	User save(User user);

	User find(String username);
}
