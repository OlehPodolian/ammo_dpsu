package oleg.podolyan.ammodpsu.domain.military;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
public class Department {

	@Id
	@GeneratedValue
	@Column(name = "department_id", insertable = true, updatable = true)
	private Long id;

	@Column(name = "parent_id")
	private Long parentId;

	private String name;

	@Column(name = "chief_id")
	private Long chiefId;

	@OneToMany
	@JoinColumn(name = "department_id")
	private Set<Department> departments = new HashSet<>();

	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
	private Set<Soldier> soldiers = new HashSet<>();

	public void addDepartment(Department department){
		department.setParentId(getId());
		departments.add(department);
	}
}
