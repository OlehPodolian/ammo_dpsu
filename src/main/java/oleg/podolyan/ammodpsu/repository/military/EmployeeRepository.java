package oleg.podolyan.ammodpsu.repository.military;

import oleg.podolyan.ammodpsu.domain.military.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByUsername(String username);

	boolean existsByUsername(String username);

	List<Employee> findByPersonalInfoLastName(String lastName);
}
