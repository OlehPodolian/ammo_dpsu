package oleg.podolyan.ammodpsu.domain.military;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

	@Id
	@GeneratedValue
	@Column(name = "department_id", insertable = true, updatable = true)
	private Long id;
}
