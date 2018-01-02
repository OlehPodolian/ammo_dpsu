package oleg.podolyan.ammodpsu.service.impl;

import oleg.podolyan.ammodpsu.domain.user.User;
import oleg.podolyan.ammodpsu.repository.user.UserRepository;
import oleg.podolyan.ammodpsu.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public String getCurrentUserPrint() {
		return "Podolyan O.I.";
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
}
