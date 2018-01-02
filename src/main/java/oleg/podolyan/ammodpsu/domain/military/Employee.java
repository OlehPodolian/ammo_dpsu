package oleg.podolyan.ammodpsu.domain.military;

import lombok.Data;
import lombok.NoArgsConstructor;
import oleg.podolyan.ammodpsu.domain.user.PersonalInfo;
import oleg.podolyan.ammodpsu.domain.user.User;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
@AttributeOverrides({
		@AttributeOverride(name = "user_id", column = @Column(name = "employee_id")),
		@AttributeOverride(name = "username", column = @Column(name = "username"))
})
@Data
@NoArgsConstructor
public class Employee extends User {

	@Embedded
	private PersonalInfo personalInfo;

	// TODO
//	private Set<InventoryItem> inventoryItems = new HashSet<>();
}
