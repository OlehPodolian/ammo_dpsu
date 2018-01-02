package oleg.podolyan.ammodpsu;

import oleg.podolyan.ammodpsu.domain.user.User;
import oleg.podolyan.ammodpsu.domain.user.security.Role;
import oleg.podolyan.ammodpsu.domain.user.security.RoleType;
import oleg.podolyan.ammodpsu.domain.user.security.UserRole;
import oleg.podolyan.ammodpsu.service.RoleService;
import oleg.podolyan.ammodpsu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class AmmoDpsuApplication {

//	@Autowired
//	private RoleService roleService;
//
//	@Autowired
//	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AmmoDpsuApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {

		};
	}

	private void saveRoles(RoleService roleService){
		if(!(roleService.exists(RoleType.ADMIN.getValue()))){
			roleService.save(new Role(RoleType.ADMIN.getValue()));
		}
		if(!(roleService.exists(RoleType.MANAGER.getValue()))){
			roleService.save(new Role(RoleType.MANAGER.getValue()));
		}
		if(!(roleService.exists(RoleType.USER.getValue()))){
			roleService.save(new Role(RoleType.USER.getValue()));
		}
	}

	private User getUser(String username){
		User user = new User();
		user.setUsername(username);
		user.setPassword("pass");
		user.setLastName("Podolyan");
		user.setFirstName("Oleg");
		user.setFatherName("Ivanovich");
		user.setPhoneNumber("0672989761");
		user.setPosition("I don't know who I am");

//		user.getUserRoles().add(new UserRole(user, roleService.find(RoleType.ADMIN.getValue())));
		return user;
	}
}
