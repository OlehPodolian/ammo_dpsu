package oleg.podolyan.ammodpsu.service;

import oleg.podolyan.ammodpsu.domain.user.User;

public interface UserService {

	String getCurrentUserPrint();

	User save(User user);
}
