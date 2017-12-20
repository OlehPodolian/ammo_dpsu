package oleg.podolyan.ammodpsu;

import oleg.podolyan.ammodpsu.domain.libs.ClothesType;
import oleg.podolyan.ammodpsu.domain.security.Role;
import oleg.podolyan.ammodpsu.domain.security.UserType;
import oleg.podolyan.ammodpsu.repository.ClothesTypeRepository;
import oleg.podolyan.ammodpsu.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class AmmoDpsuApplication {

	private final RoleService roleService;
	private final ClothesTypeRepository clothesTypeRepository;

	public AmmoDpsuApplication(RoleService roleService, ClothesTypeRepository clothesTypeRepository) {
		this.roleService = roleService;
		this.clothesTypeRepository = clothesTypeRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AmmoDpsuApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {
			saveRoles();
			ClothesType clothesShirt = new ClothesType("SHIRT");
			System.out.println("Clothes type: " + clothesShirt.getValue());
			clothesShirt.getSizes().addAll(Arrays.asList("39", "40", "41", "42"));
			clothesTypeRepository.save(clothesShirt);

		};
	}

	private void saveRoles(){
		if(!(roleService.existsByName(UserType.ADMIN.getValue()))){
			roleService.save(new Role(UserType.ADMIN.getValue()));
		}
		if(!(roleService.existsByName(UserType.MANAGER.getValue()))){
			roleService.save(new Role(UserType.MANAGER.getValue()));
		}
		if(!(roleService.existsByName(UserType.USER.getValue()))){
			roleService.save(new Role(UserType.USER.getValue()));
		}
	}
}
