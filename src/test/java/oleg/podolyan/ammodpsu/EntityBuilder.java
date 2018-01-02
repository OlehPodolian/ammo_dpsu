package oleg.podolyan.ammodpsu;

import oleg.podolyan.ammodpsu.domain.military.Employee;
import oleg.podolyan.ammodpsu.domain.military.Soldier;
import oleg.podolyan.ammodpsu.domain.user.PersonalInfo;
import oleg.podolyan.ammodpsu.domain.user.User;
import oleg.podolyan.ammodpsu.domain.user.security.RoleType;
import oleg.podolyan.ammodpsu.domain.user.security.UserRole;
import oleg.podolyan.ammodpsu.domain.warehouse.SizeType;
import oleg.podolyan.ammodpsu.repository.user.UserRepository;

import java.time.LocalDateTime;

public class EntityBuilder {

	public static User buildUser(String username){
		User user = new User();
		user.setUsername(username);
		user.setPassword("pass");
		user.setPhoneNumber("0672989761");

		// manual auditing
		user.setCreated(LocalDateTime.now());
		user.setCreatedBy("Developer");
		user.setLastModified(LocalDateTime.now());
		user.setLastModifiedBy("Developer");

		return user;
	}

	public static Soldier buildSoldier(String username) {
		Soldier soldier = new Soldier();
		soldier.setUsername(username);
		soldier.setPassword("pass");
		soldier.setPersonalInfo(new PersonalInfo("Ivanov", "Ivan", "Ivanovich", "Book-keeper"));
		soldier.setPhoneNumber("0672989761");
		soldier.setRank("major");
		soldier.getSizeMap().put(SizeType.HEAD, "57");
		soldier.getSizeMap().put(SizeType.COLLAR, "43");
		soldier.getSizeMap().put(SizeType.FOOT, "43");
		soldier.getSizeMap().put(SizeType.BODY, "52-5");

		// manual auditing
		soldier.setCreated(LocalDateTime.now());
		soldier.setCreatedBy("Developer");
		soldier.setLastModified(LocalDateTime.now());
		soldier.setLastModifiedBy("Developer");

		return soldier;
	}

	public static Employee buildEmployee(String username){
		Employee employee = new Employee();
		employee.setUsername(username);
		employee.setPassword("pass");
		employee.setPersonalInfo(new PersonalInfo("Ivanov", "Ivan", "Ivanovich", "Book-keeper"));
		employee.setPhoneNumber("0672989761");

		// manual auditing
		employee.setCreated(LocalDateTime.now());
		employee.setCreatedBy("Developer");
		employee.setLastModified(LocalDateTime.now());
		employee.setLastModifiedBy("Developer");

		return employee;
	}

}
